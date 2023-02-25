import Dependencies._

name := "gol"

ThisBuild / scalaVersion := "3.2.2"

lazy val gol = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    libraryDependencies += "org.scalatest" %%% "scalatest" % Versions.scalaTest % Test
  )
  .jsSettings(
    libraryDependencies += ("org.scala-js" %%% "scalajs-dom" % "2.4.0"),
    scalaJSUseMainModuleInitializer := true
  )

lazy val benchmarkProject = Project("bench", file("benchmark"))
  .dependsOn(gol.jvm)
  .enablePlugins(JmhPlugin)
