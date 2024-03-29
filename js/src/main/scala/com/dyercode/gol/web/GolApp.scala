package com.dyercode.gol.web

import com.dyercode.gol.*
import com.dyercode.gol.web.GolApp.GameState.*
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, document, window}

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.*
import scala.scalajs.js.timers.setInterval
import scala.util.{Failure, Success}

object GolApp {

  @main def run(): Unit = {
    val standard = appendCanvas(document.getElementById("standard"))
    renderGame(standard)
    val windy = appendCanvas(document.getElementById("windy"))
    renderGame(windy, Config(true))
  }

  val cellsWide = 60
  val cellsHigh = 25
  val gridWidth: Int = cellsWide * 10
  val gridHeight: Int = cellsHigh * 10
  val gridSize = 10

  val gridPadding = 10
  val canvasWidth: Int = gridWidth + (gridPadding * 2) + 1
  val canvasHeight: Int = gridHeight + (gridPadding * 2) + 1

  enum GameState:
    case Next(board: Board, config: Config)
    case Current(board: Board, config: Config)
  case class Config(windy: Boolean)
  val windy = Config(true)
  val standard = Config(false)

  def renderGame(c: dom.html.Canvas, config: Config = standard): Unit = {
    val ctx: CanvasRenderingContext2D = c
      .getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    val board = prepBoard

    drawBoard(ctx)
    drawNodes(ctx, board)
    requestAnimationFrame(ctx, None, Current(board, config))
  }

  def fps(fps: Double): Double = 1000.0 / fps

  def draw(ctx: CanvasRenderingContext2D, board: Board): Unit = {
    ctx.clearRect(0, 0, canvasWidth, canvasHeight)
    drawBoard(ctx)
    drawNodes(ctx, board)
  }

  def requestAnimationFrame(
      ctx: CanvasRenderingContext2D,
      lastFrame: Option[Double],
      state: GameState
  ): Int = window.requestAnimationFrame(
    animationLoop(ctx, lastFrame, state)(_)
  )

  def blowBoard(inBoard: Board): Board = {
    var blownBoard = emptyBoard
    (0 until cellsWide).foreach { x =>
      (0 until cellsHigh).foreach { y =>
        inBoard(x, y) match {
          case Cell.Alive => {
            blownBoard = blownBoard.addCell(x + 1, y)
          }
          case Cell.Dead => ()
        }
      }
    }
    blownBoard
  }

  // timestamp = millis
  def animationLoop(
      ctx: CanvasRenderingContext2D,
      lastFrame: Option[Double],
      gameState: GameState,
      windy: Boolean = false // todo - accept an algorithm instead
  )(
      timestamp: Double
  ): Unit = {
    gameState match {
      case Current(board, config) =>
        Future {
          val tickedBoard = tick(board, Size(cellsWide, cellsHigh))
          if (config.windy) { blowBoard(tickedBoard) }
          else { tickedBoard }
        }
          .onComplete {
            case Success(next: Board) =>
              requestAnimationFrame(ctx, lastFrame, Next(next, config))
            case Failure(_) =>
              println("error running tick, retrying")
              requestAnimationFrame(ctx, lastFrame, gameState)
          }
      case Next(state, config) =>
        lastFrame match {
          case None => requestAnimationFrame(ctx, Some(timestamp), gameState)
          case Some(last) if timestamp - last >= fps(4) =>
            draw(ctx, state)
            requestAnimationFrame(ctx, None, Current(state, config))
          case Some(last) =>
            requestAnimationFrame(ctx, lastFrame, gameState)
        }
    }
  }

  def drawBoard(ctx: CanvasRenderingContext2D): Unit = {
    (0 to gridWidth by gridSize).foreach { i =>
      ctx.moveTo(0.5 + i + gridPadding, gridPadding)
      ctx.lineTo(0.5 + i + gridPadding, gridHeight + gridPadding)
    }

    (0 to gridHeight by gridSize).foreach { j =>
      ctx.moveTo(gridPadding, 0.5 + j + gridPadding)
      ctx.lineTo(gridWidth + gridPadding, 0.5 + j + gridPadding)
    }

    ctx.strokeStyle = "black"
    ctx.stroke()
  }

  def drawNodes(ctx: CanvasRenderingContext2D, board: Board): Unit = {
    (0 until cellsWide).foreach { i =>
      (0 until cellsHigh).foreach { j =>
        if (board(i, j) == Cell.Alive) {
          ctx.fillRect(
            (0.1 * i * 10 * gridPadding) + 3.0 + gridPadding,
            (0.1 * j * 10 * gridPadding) + 2.5 + gridPadding,
            6.0,
            6.0
          )
        }
      }
    }
  }

  def appendCanvas(targetNode: dom.Node): dom.html.Canvas = {
    val canvas = document.createElement("canvas").asInstanceOf[dom.html.Canvas]
    canvas.width = canvasWidth
    canvas.height = canvasHeight
    targetNode.appendChild(canvas)
    canvas
  }

  def prepBoard: Board = {
    // Pulsar
    emptyBoard
      .addCell(3, 4)
      .addCell(3, 5)
      .addCell(3, 6)
      .addCell(3, 10)
      .addCell(3, 11)
      .addCell(3, 12)
      .addCell(5, 2)
      .addCell(5, 7)
      .addCell(5, 9)
      .addCell(5, 14)
      .addCell(6, 2)
      .addCell(6, 7)
      .addCell(6, 9)
      .addCell(6, 14)
      .addCell(7, 2)
      .addCell(7, 7)
      .addCell(7, 9)
      .addCell(7, 14)
      .addCell(8, 4)
      .addCell(8, 5)
      .addCell(8, 6)
      .addCell(8, 10)
      .addCell(8, 11)
      .addCell(8, 12)
      .addCell(10, 4)
      .addCell(10, 5)
      .addCell(10, 6)
      .addCell(10, 10)
      .addCell(10, 11)
      .addCell(10, 12)
      .addCell(11, 2)
      .addCell(11, 7)
      .addCell(11, 9)
      .addCell(11, 14)
      .addCell(12, 2)
      .addCell(12, 7)
      .addCell(12, 9)
      .addCell(12, 14)
      .addCell(13, 2)
      .addCell(13, 7)
      .addCell(13, 9)
      .addCell(13, 14)
      .addCell(15, 4)
      .addCell(15, 5)
      .addCell(15, 6)
      .addCell(15, 10)
      .addCell(15, 11)
      .addCell(15, 12)
  }
}
