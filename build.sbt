import Dependencies._

name := "gol"

ThisBuild / scalaVersion := "2.13.6"

lazy val golProject = (
  Project("gol", file("."))
    settings (libraryDependencies ++= golDependencies)
)
