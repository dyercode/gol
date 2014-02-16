package com.dyercode.gol

import org.scalatest.{MustMatchers, FunSuite}

class GameSuite extends FunSuite with MustMatchers {

  test("addNewCell must add a cell to the board at the given coordinates") {
    val board = addNewCell(getEmptyBoard, 1,1)
    board(1,1) mustBe true
  }

  test("addNewCell must maintain previous cells in the board") {
    val board = addNewCell(getEmptyBoard, 1,1)
    val newBoard = addNewCell(board, 3,3)
    newBoard(1,1) mustBe true
    newBoard(3,3) mustBe true
  }

  test("addNewCell must not have cells that weren't added") {
    val board = addNewCell(getEmptyBoard, 1,1)
    val newBoard = addNewCell(board, 3,3)
    newBoard(4,4) mustBe false
    newBoard(7,9) mustBe false
  }
}
