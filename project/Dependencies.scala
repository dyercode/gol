import sbt._
import Keys._

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"

  val golDependencies = Seq(scalaTest % "test")
}
