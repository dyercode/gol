package com.dyercode

import scala.annotation.tailrec


package object gol {

  type Board = (Int, Int) => Boolean

  def getEmptyBoard: Board = (x: Int, y: Int) => false

  def addNewCell(board: Board, x: Int, y: Int): Board = {
    @tailrec val fun:Board = (newX: Int, newY: Int) => {
      if (newX == x && newY == y) {
        true
      } else {
        board(newX, newY)
      }
    }
    fun
  }

}
