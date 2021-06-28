import Dependencies._

name := "gol"

ThisBuild / scalaVersion := "3.0.0"

lazy val golProject = project
  .in(file("."))
  .aggregate(golCross.js, golCross.jvm)

lazy val golCross = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    libraryDependencies += "org.scalatest" %%% "scalatest" % Versions.scalaTest % Test
  )
  .jsSettings(
    libraryDependencies += ("org.scala-js" %%% "scalajs-dom" % "1.1.0")
      .cross(CrossVersion.for3Use2_13),
    scalaJSUseMainModuleInitializer := true
  )

lazy val benchmarkProject = (
  Project("bench", file("benchmark"))
).dependsOn(golProject)
  .enablePlugins(JmhPlugin)
