package com.dyercode.gol

import com.dyercode.gol.Cell.{Alive, Dead}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class GameSuite extends AnyFunSuite with must.Matchers {

  test("addLiveCell must add a cell to the board at the given coordinates") {
    val board = addLiveCell(emptyBoard, 1, 1)
    board(1, 1) mustBe Alive
  }

  test("addLiveCell must maintain previous cells in the board") {
    val board = addLiveCell(emptyBoard, 1, 1)
    val newBoard = addLiveCell(board, 3, 3)
    newBoard(1, 1) mustBe Alive
    newBoard(3, 3) mustBe Alive
  }

  // Todo - property test this instead of hardcoded wrong cells
  test("addLiveCell must not have living cells that weren't added") {
    val board = addLiveCell(emptyBoard, 1, 1)
    val newBoard = addLiveCell(board, 3, 3)
    newBoard(4, 4) mustBe Dead
    newBoard(7, 9) mustBe Dead
  }

  test(
    "Any live cell with fewer than two live neighbours dies, as if caused by underpopulation."
  ) {
    val board = addLiveCell(emptyBoard, 1, 1)
    val nextGeneration = tick(board, Size(3, 3))
    nextGeneration(1, 1) mustBe Dead
  }

  test(
    "Any live cell with more than three live neighbours dies, as if by overcrowding."
  ) {
    val testCell = emptyBoard.add(1, 1)
    val board =
      testCell
        .add(2, 0)
        .add(2, 1)
        .add(2, 2)
        .add(0, 1)
    val nextGeneration = tick(board, Size(3, 3))
    nextGeneration(1, 1) mustBe Dead
  }

  test(
    "Any live cell with two or three live neighbours lives on to the next generation."
  ) {
    val testCell = emptyBoard.add(1, 1)
    val twoLivingNeighbors = testCell.add(2, 0).add(2, 1)
    val threeLivingNeighbors = twoLivingNeighbors.add(2, 2)
    tick(twoLivingNeighbors, Size(3, 3))(1, 1) mustBe Alive
    tick(threeLivingNeighbors, Size(3, 3))(1, 1) mustBe Alive
  }

  test(
    "Any dead cell with exactly three live neighbours becomes a live cell."
  ) {
    val twoLivingNeighbors = emptyBoard.add(2, 0).add(2, 1)
    val threeLivingNeighbors = twoLivingNeighbors.add(2, 2)
    tick(twoLivingNeighbors, Size(3, 3))(1, 1) mustBe Dead
    tick(threeLivingNeighbors, Size(3, 3))(1, 1) mustBe Alive
  }
}
