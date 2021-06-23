import Dependencies._

name := "gol"

ThisBuild / scalaVersion := "3.0.0"

lazy val golProject = (
  Project("gol", file("."))
    settings (libraryDependencies ++= golDependencies)
)
