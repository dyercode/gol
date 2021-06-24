package com.dyercode.gol.web

import com.dyercode.gol._
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, document}

import scala.annotation.tailrec
import scala.concurrent.duration._
import scala.scalajs.js.timers.setInterval

object GolApp {

  @main def run(): Unit = {
    val canvas = appendCanvas(document.body)
    renderGame(canvas)
  }

  val cellsWide = 40
  val cellsHigh = 40
  val gridWidth = cellsWide * 10
  val gridHeight = cellsHigh * 10
  val gridSize = 10

  val gridPadding = 10
  val canvasWidth = gridWidth + (gridPadding * 2) + 1
  val canvasHeight = gridHeight + (gridPadding * 2) + 1

  def renderGame(c: dom.html.Canvas): Unit = {
    val ctx: CanvasRenderingContext2D = c
      .getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    var board = prepBoard()

    drawBoard(ctx)
    drawNodes(ctx, board)

    setInterval(250.millis) {
      board = tick(board, Size(cellsWide, cellsHigh))
      ctx.clearRect(0, 0, canvasWidth, canvasHeight)
      drawBoard(ctx)
      drawNodes(ctx, board)
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
    (0 to cellsWide - 1).foreach { i =>
      (0 to cellsHigh - 1).foreach { j =>
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

  def prepBoard(): Board = {
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
