package com.dyercode.gol

enum Cell:
  case Alive, Dead

import Cell._
type Board = (Int, Int) => Cell

def emptyBoard: Board = (_: Int, _: Int) => Dead

def addLiveCell(board: Board, x: Int, y: Int): Board = {
  (newX: Int, newY: Int) =>
    if (newX == x && newY == y)
      Alive
    else
      board(newX, newY)
}

def neighbors(x: Int, y: Int): Seq[(Int, Int)] = {
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

case class Size(width: Int, height: Int)
def tick(board: Board, size: Size): Board = {
  val nn: Seq[((Int, Int), Seq[(Int, Int)])] = for {
    x <- 0 to size.width
    y <- 0 to size.height
  } yield ((x, y), neighbors(x, y))

  nn.foldLeft(emptyBoard) {
    case (acc: Board, ((x, y), neighbors): ((Int, Int), Seq[(Int, Int)])) =>
      val liveNeighborCount = countNeighbors(board, neighbors)
      board(x, y) match {
        case Alive if liveNeighborCount < 2 || liveNeighborCount > 3 =>
          acc
        case Alive                          => addLiveCell(acc, x, y)
        case Dead if liveNeighborCount == 3 => addLiveCell(acc, x, y)
        case Dead                           => acc
      }
  }
}

private def countNeighbors(board: Board, neighbors: Seq[(Int, Int)]) = {
  neighbors.count { case (x, y) =>
    board(x, y) == Alive
  }
}
