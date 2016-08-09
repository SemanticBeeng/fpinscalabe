import sbt._
import Keys._

object depends {

  lazy val specs2Version = settingKey[String]("defines the current specs2 version")
  lazy val scalazVersion = settingKey[String]("defines the current scalaz version")

  lazy val classycle = Seq("org.specs2" % "classycle" % "1.4.3")

  def compiler(scalaVersion: String) = Seq("org.scala-lang" % "scala-compiler" % scalaVersion)

  def reflect(scalaVersion: String) = Seq("org.scala-lang" % "scala-reflect" % scalaVersion)

  def specs2(specs2Version: String) =
    Seq("org.specs2"        %% "specs2-core",
        "org.specs2"        %% "specs2-form",
        "org.specs2"        %% "specs2-html",
        "org.specs2"        %% "specs2-markdown",
        "org.specs2"        %% "specs2-scalacheck").map(_ % specs2Version)

  def scalaz(scalazVersion: String) =
    Seq("org.scalaz"      %% "scalaz-core",
        "org.scalaz"      %% "scalaz-effect",
        "org.scalaz"      %% "scalaz-concurrent",
        "org.scalaz"      %% "scalaz-scalacheck-binding").map(_ % scalazVersion)

  //  def scalaParser(scalaVersion: String) =
//    PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion)){
//      case Some((2, scalaMajor)) if scalaMajor >= 11 =>
//        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
//    }.toList

//  def scalaXML(scalaVersion: String) =
//    PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion)){
//      case Some((2, scalaMajor)) if scalaMajor >= 11 =>
//        "org.scala-lang.modules" %% "scala-xml" % "1.0.5"
//    }.toList

  def kindp(scalaVersion: String) =
    if (scalaVersion startsWith "2.12.0-M4")
      "org.spire-math" % "kind-projector_2.12.0-M3" % "0.7.1"
    else
      "org.spire-math" % "kind-projector" % "0.7.1" cross CrossVersion.binary

  def scalacheck(scalaVersion: String) =
    Seq("org.scalacheck" %% "scalacheck"    % "1.13.1")

  def simulacrum(scalaVersion: String) =
    Seq("com.github.mpilquist" % "simulacrum_2.11" % "0.7.0")

  lazy val mockito       = Seq("org.mockito"    %  "mockito-core"  % "1.9.5")
  lazy val junit         = Seq("junit"          %  "junit"         % "4.12")
  lazy val hamcrest      = Seq("org.hamcrest"   %  "hamcrest-core" % "1.3")

  def shapeless(scalaVersion: String) =
    Seq("com.chuusai" %% "shapeless" % "2.3.1")

  def cats = Seq("org.typelevel" %% "cats" % "0.6.1")

//  lazy val pegdown = Seq("org.pegdown" % "pegdown" % "1.2.1")

//  lazy val testInterface = Seq("org.scala-sbt"  % "test-interface" % "1.0")

  lazy val tagsoup = "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2"

  def paradise(scalaVersion: String) =
    //if (scalaVersion.startsWith("2.11") || scalaVersion.startsWith("2.12")) Nil
    /*else  */Seq(compilerPlugin("org.scalamacros" %% "paradise"    % "2.1.0" cross CrossVersion.full)/*,
                             "org.scalamacros" %% "quasiquotes" % "2.1.0"*/)

  lazy val resolvers =
    Seq(updateOptions := updateOptions.value.withCachedResolution(true)) ++ {
      sbt.Keys.resolvers ++=
      Seq(
        Resolver.sonatypeRepo("releases"),
        Resolver.sonatypeRepo("snapshots"),
        Resolver.typesafeIvyRepo("releases"),
        "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases")
    }

}


