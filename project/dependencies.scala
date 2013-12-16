import sbt._
import Keys._

object Dependencies {
	val scalaTest = "org.scalatest" %% "scalatest" % "2.0"
	val golDependencies = Seq(scalaTest % "test") 
}
