/** Project */
name := "fpinscalabe"

organization := "org.specs2"

scalaVersion := "2.11.8"

/** Shell */
shellPrompt := { state => "fpinscalabe> " }

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++=
  Seq(
    "org.specs2"      %% "specs2-core"    % "3.7",
    "org.specs2"      %% "specs2-form"    % "3.7",
    "org.specs2"      %% "specs2-html"    % "3.7",
    // Scalaz
    "org.scalaz"      %% "scalaz-core"    % "7.2.4",
    // Cats
    "org.typelevel"   %% "cats"           % "0.6.0-M1",
    "com.chuusai"     %% "shapeless"      % "2.3.1",
    // Simulacrum
    "com.github.mpilquist" % "simulacrum_2.11" % "0.7.0")

scalacOptions ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature", "-language:_")

logBuffered := false

/** Console */
initialCommands in console in Test := "import org.specs2._"

