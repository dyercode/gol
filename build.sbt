import Dependencies._

name := "gol"

ThisBuild / scalaVersion := "3.0.0"

lazy val golProject = (
  Project("gol", file("gol"))
    settings (libraryDependencies ++= golDependencies)
)

lazy val webappProject = (
  Project("web", file("web"))
    settings (libraryDependencies ++= webappDependencies,
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += ("org.scala-js" %%% "scalajs-dom" % "1.1.0")
      .cross(CrossVersion.for3Use2_13))
).dependsOn(golProject)
