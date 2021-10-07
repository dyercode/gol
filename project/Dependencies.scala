//import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._
//import Def.{setting => dep}
import Keys._

object Dependencies {
  object Versions {
    val scalaTest = "3.2.10"
  }
  // scalajs breaks using these here since %%% is a macro that only works in sbt dsl
//  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"
//  val scalajsDom = dep("org.scala-js" %%% "scalajs-dom" % "1.1.0").value

//  val golDependencies = Seq(scalaTest % Test)
//  val webappDependencies = Seq(scalaTest % Test)
}
