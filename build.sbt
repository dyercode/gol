import Dependencies._

name := "gol"

ThisBuild / scalaVersion := "3.0.0"

lazy val golProject = (
  Project("gol", file("gol"))
    settings (libraryDependencies += "org.scalatest" %%% "scalatest" % Versions.scalaTest % Test)
)

lazy val benchmarkProject = (
  Project("bench", file("benchmark"))
).dependsOn(golProject)
  .enablePlugins(JmhPlugin)

lazy val webappProject = (
  Project("web", file("web"))
    settings (
      libraryDependencies += ("org.scala-js" %%% "scalajs-dom" % "1.1.0")
        .cross(CrossVersion.for3Use2_13),
      scalaJSUseMainModuleInitializer := true
    )
).dependsOn(golProject)
