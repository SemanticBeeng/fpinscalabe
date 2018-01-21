import sbt._
import Keys._

object versions {
  val scala = "2.12.4"

  val scalaCheck = "1.13.5"
  val discipline = "0.8"

  val scalaMacrosParadise = "2.1.0"
  val kindProjector = "0.9.5"
  val simulacrum = "0.11.0"
  val tagsoup = "1.2"

  val shapeless = "2.3.3"

  val scalaz = "7.2.15"

  val cats = "1.0.1"
  val catsEffect = "0.5"

  val fs2 = "0.10.0-RC1"
  val fs2cats = "0.5.0"
  val fs2scalaz = "0.3.0"

  val doobie = "0.5.0-M6"

  /**
    * from @etorreborre: "The problem comes from the fact that I think you are checking laws with scalaz-scalacheck-bindings
    * which depends on scalacheck 1.12.5 only.
    * One alternative would be to use cats only and the typelevel discipline project to check laws."
    */
  val specs2 = "4.0.2"
}

object depends {

  lazy val specs2Version = settingKey[String]("defines the current specs2 version")
  // lazy val scalazVersion = settingKey[String]("defines the current scalaz version")

  //lazy val classycle = Seq("org.specs2" % "classycle" % "1.4.3")

  //def compiler(scalaVersion: String) = Seq("org.scala-lang" % "scala-compiler" % scalaVersion)

  def reflect(scalaVersion: String) = Seq("org.scala-lang" % "scala-reflect" % scalaVersion)

  def specs2(specs2Version: String) =
    Seq("org.specs2"        %% "specs2-core",
        "org.specs2"        %% "specs2-form",
        "org.specs2"        %% "specs2-html",
        "org.specs2"        %% "specs2-markdown",
        "org.specs2"        %% "specs2-scalacheck").map(_ % specs2Version)

  def scalaz(/*scalazVersion: String*/) =
    Seq("org.scalaz"      %% "scalaz-core",
        "org.scalaz"      %% "scalaz-effect",
        "org.scalaz"      %% "scalaz-concurrent",
        "org.scalaz"      %% "scalaz-scalacheck-binding").map(_ % versions.scalaz)

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
      "org.spire-math" % "kind-projector_2.12.0-M3" % versions.kindProjector
    else
      "org.spire-math" % "kind-projector" % versions.kindProjector cross CrossVersion.binary

  def scalacheck(scalaVersion: String) =
    Seq("org.scalacheck" %% "scalacheck"    % versions.scalaCheck)

  def discipline(scalaVersion: String) =
    Seq(  "org.typelevel" %% "discipline" % versions.discipline)

  def simulacrum(scalaVersion: String) =
    Seq("com.github.mpilquist" % "simulacrum_2.12" % versions.simulacrum)

  //lazy val mockito       = Seq("org.mockito"    %  "mockito-core"  % "1.9.5")
  //lazy val junit         = Seq("junit"          %  "junit"         % "4.12")
  //lazy val hamcrest      = Seq("org.hamcrest"   %  "hamcrest-core" % "1.3")

  def shapeless(scalaVersion: String) =
    Seq("com.chuusai" %% "shapeless" % versions.shapeless)

  def fs2() =
    Seq("co.fs2" %% "fs2-core" % versions.fs2,
        "co.fs2" %% "fs2-io" % versions.fs2,
        "co.fs2" %% "fs2-cats" % versions.fs2cats,
        "co.fs2" %% "fs2-scalaz" % versions.fs2scalaz)

  def cats() =
    Seq("org.typelevel" %% "cats-kernel",
        "org.typelevel" %% "cats-macros",
        "org.typelevel" %% "cats-core",
        "org.typelevel" %% "cats-laws",
        "org.typelevel" %% "cats-free",
        "org.typelevel" %% "cats-testkit").map(_ % versions.cats) ++
    Seq("org.typelevel" %% "cats-effect").map(_ % versions.catsEffect)

//  lazy val pegdown = Seq("org.pegdown" % "pegdown" % "1.2.1")

//  lazy val testInterface = Seq("org.scala-sbt"  % "test-interface" % "1.0")

  lazy val tagsoup = "org.ccil.cowan.tagsoup" % "tagsoup" % versions.tagsoup

  def paradise(scalaVersion: String) =
    /*if (scalaVersion.startsWith("2.11") || scalaVersion.startsWith("2.12"))
       Nil
    else  */
    Seq(compilerPlugin("org.scalamacros" %% "paradise"    % versions.scalaMacrosParadise cross CrossVersion.full)/*,
                       "org.scalamacros" %% "quasiquotes" % versions.scalaMacrosParadise*/)

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
