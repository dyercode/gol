package com.dyercode.gol

object gol {

  type Board = (Int, Int) => Boolean

  def getEmptyBoard: Board = (_: Int, _: Int) => false

  def addNewCell(board: Board, x: Int, y: Int): Board = {
    (newX: Int, newY: Int) =>
      (newX == x && newY == y) || board(newX, newY)
  }

}
