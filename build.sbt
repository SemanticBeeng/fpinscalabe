/** Project */
name := "fpinscalabe"

organization := "org.specs2"

scalaVersion := "2.11.8"

/** Shell */
shellPrompt := { state => "fpinscalabe> " }

libraryDependencies ++=
  Seq(
    "org.specs2"      %% "specs2-core"    % "3.7",
    "org.specs2"      %% "specs2-form"    % "3.7",
    "org.specs2"      %% "specs2-html"    % "3.7",
    // Scalaz
    "org.scalaz"      %% "scalaz-core"    % "7.2.3",
    // Cats
    "org.typelevel"   %% "cats"           % "0.6.0-M1",
    "com.chuusai"     %% "shapeless"      % "2.3.1"
  )

scalacOptions ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature", "-language:_")

logBuffered := false

/** Console */
initialCommands in console in Test := "import org.specs2._"

