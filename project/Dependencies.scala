//import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._
//import Def.{setting => dep}
import Keys._

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"
//  val scalajsDom = dep("org.scala-js" %%% "scalajs-dom" % "1.1.0").value

  val golDependencies = Seq(scalaTest % Test)
  val webappDependencies = Seq(scalaTest % Test)
}
