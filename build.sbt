import Dependencies._

name := "gol"

scalaVersion in ThisBuild := "2.10.3"

lazy val golProject = (
  Project("gol", file("."))
  settings(libraryDependencies ++= golDependencies)
)
