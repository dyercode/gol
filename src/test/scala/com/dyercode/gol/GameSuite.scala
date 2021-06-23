package com.dyercode.gol

import com.dyercode.gol.Cell.{Alive, Dead}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class GameSuite extends AnyFunSuite with must.Matchers {

  test("addNewCell must add a cell to the board at the given coordinates") {
    val board = addNewCell(emptyBoard, 1, 1)
    board(1, 1) mustBe Alive
  }

  test("addNewCell must maintain previous cells in the board") {
    val board = addNewCell(emptyBoard, 1, 1)
    val newBoard = addNewCell(board, 3, 3)
    newBoard(1, 1) mustBe Alive
    newBoard(3, 3) mustBe Alive
  }

  // Todo - property test this instead of hardcoded wrong cells
  test("addNewCell must not have cells that weren't added") {
    val board = addNewCell(emptyBoard, 1, 1)
    val newBoard = addNewCell(board, 3, 3)
    newBoard(4, 4) mustBe Dead
    newBoard(7, 9) mustBe Dead
  }

  test(
    "Any live cell with fewer than two live neighbours dies, as if caused by underpopulation."
  ) {
    val board = addNewCell(emptyBoard, 1, 1)
    val nextGeneration = evolve(board, Size(3, 3))
    nextGeneration(1, 1) mustBe Dead
  }

  test(
    "Any live cell with more than three live neighbours dies, as if by overcrowding."
  ) {
    val testCell = addNewCell(emptyBoard, 1, 1)
    val board = addNewCell(
      addNewCell(addNewCell(addNewCell(testCell, 2, 0), 2, 1), 2, 2),
      0,
      1
    )
    val nextGeneration = evolve(board, Size(3, 3))
    nextGeneration(1, 1) mustBe Dead
  }
}
