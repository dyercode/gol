package com.dyercode.gol

import org.openjdk.jmh.annotations.{
  Benchmark,
  BenchmarkMode,
  Mode,
  OutputTimeUnit
}

import java.util.concurrent.TimeUnit

class GolBenchmark {
  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  @OutputTimeUnit(TimeUnit.SECONDS)
  def measureThroughput(): Unit = tick(pulsar, Size(50, 50))
}

val pulsar: Board = {
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
