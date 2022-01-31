package com.dyercode.gol

enum Cell:
  case Alive, Dead

import com.dyercode.gol.Cell.*

type Board = (Int, Int) => Cell

extension (board: Board) {
  def addCell(x: Int, y: Int): Board = addLiveCell(board, x, y)
}

def emptyBoard: Board = (_: Int, _: Int) => Dead

def addLiveCell(board: Board, x: Int, y: Int): Board = {
  (newX: Int, newY: Int) =>
    if (newX == x && newY == y)
      Alive
    else
      board(newX, newY)
}

def tickCell(x: Int, y: Int, board: Board): Cell = {
  (board(x, y), countNeighbors(board, neighbors(x, y))) match {
    case (_, 3)     => Alive
    case (Alive, 2) => Alive
    case _          => Dead
  }
}

def tick(board: Board, bounds: Bounds): Board = {
  var res: Board = emptyBoard
  for {
    x <- bounds.minX to bounds.maxX
    y <- bounds.minY to bounds.maxY
  } {
    tickCell(x, y, board) match {
      case Alive => res = res.addCell(x, y)
      case Dead  =>
    }
  }
  res
}

private def neighbors(x: Int, y: Int): Seq[(Int, Int)] = {
  Seq(
    (x + 1, y),
    (x + 1, y + 1),
    (x + 1, y - 1),
    (x, y + 1),
    (x, y - 1),
    (x - 1, y),
    (x - 1, y + 1),
    (x - 1, y - 1)
  )
}

private def countNeighbors(board: Board, neighbors: Seq[(Int, Int)]) = {
  neighbors.count { case (x, y) =>
    board(x, y) == Alive
  }
}

trait Bounds {
  def minX: Int
  def minY: Int
  def maxX: Int
  def maxY: Int
}

case class Size(width: Int, height: Int) extends Bounds {
  override val minX = 0
  override val minY = 0
  override val maxX: Int = width
  override val maxY: Int = height
}
