import sbt._
import complete.DefaultParsers._
import Keys._
import com.typesafe.sbt._
import pgp.PgpKeys._
import SbtSite._
import SiteKeys._
import SbtGit._
import GitKeys._
import SbtGhPages._
import GhPagesKeys._
import Defaults._
import xerial.sbt.Sonatype._
import SonatypeKeys._
import depends._
import ohnosequences.sbt.GithubRelease.keys._

/** MAIN PROJECT */
lazy val fpinscalabe = Project(
  id = "fpinscalabe",
  base = file("."),
  settings =
    moduleSettings("") ++
      siteSettings     ++
      Seq(name := "fpinscalabe", packagedArtifacts := Map.empty)
).aggregate(main, guide)
  .enablePlugins(GitBranchPrompt)

  /**
    *
    */
  lazy val main = Project(
    id = "main",
    base = file("main"),
    settings =
      moduleSettings("main")       ++
      Seq(
        name := "FPinScalaByExample",
        libraryDependencies ++=
        depends.scalaz(scalazVersion.value) ++
        depends.shapeless(scalaVersion.value) ++
        depends.simulacrum(scalaVersion.value) ++
        depends.catsCore ++
        depends.catsKernel ++
        depends.catsMacros ++
        depends.catsLaws ++
        depends.catsFree ++
        //depends.scalaParser(scalaVersion.value) ++
        //depends.scalaXML(scalaVersion.value) ++
        depends.reflect(scalaVersion.value) ++
        depends.paradise(scalaVersion.value) ++
        depends.discipline(scalaVersion.value) ++
        depends.scalacheck(scalaVersion.value) ++
        depends.specs2(specs2Version.value)
        //scalacOptions in codata := Seq("-feature", "-language:_"),
        // packagedArtifacts := Map.empty
        //logLevel in compile := Level.Error
      )
  )

  /**
    *
    */
  lazy val guide = Project(id = "guide", base = file("guide"),
    settings =
      Seq(libraryDependencies += depends.tagsoup) ++
        moduleSettings("guide") ++
        Seq(name := "fpinscalabe-guide") ++
        siteSettings
        //documentationSettings
  ).dependsOn(main % "compile->compile;test->test")

/** COMMON SETTINGS */
lazy val commonSettings = Seq(
  organization := "Semanticbeeng",
  specs2Version in GlobalScope := versions.specs2,
  scalazVersion in GlobalScope := versions.scalaz,
  specs2ShellPrompt,
  scalaVersion := versions.scala,
  crossScalaVersions := Seq(scalaVersion.value))

lazy val tagName = Def.setting{
  s"specs2-${version.value}"
}

lazy val specs2Version = settingKey[String]("defines the current specs2 version")
lazy val scalazVersion = settingKey[String]("defines the current scalaz version")
lazy val shapelessVersion = versions.shapeless

def moduleSettings(name: String) =
  coreDefaultSettings ++
  depends.resolvers ++
  commonSettings ++
  compilationSettings ++
  testingSettings      //++
    //publicationSettings  //++
    //notificationSettings

def moduleJvmSettings(name: String) =
  testingJvmSettings

lazy val specs2ShellPrompt = shellPrompt in ThisBuild := { state =>
  val name = Project.extract(state).currentRef.project
  (if (name == "specs2") "" else name) + "> "
}

def scalaSourceVersion(scalaBinaryVersion: String) =
  if (scalaBinaryVersion.startsWith("2.11"))
    "2.11"
  else
    "2.12"

lazy val compilationSettings = Seq(
  // https://gist.github.com/djspiewak/976cd8ac65e20e136f05
  unmanagedSourceDirectories in Compile ++=
    Seq((sourceDirectory in Compile).value / s"scala-${scalaSourceVersion(scalaBinaryVersion.value)}",
      (sourceDirectory in Compile).value / s"scala-scalaz-7.1.x",
      (sourceDirectory in (Test, test)).value / s"scala-scalaz-7.1.x"),
  maxErrors := 20,
  incOptions := incOptions.value.withNameHashing(true),
  scalacOptions in Compile ++=
      Seq("-Xfatal-warnings:false",
        "-Xlint",
        "-Ywarn-unused-import:false",
        //"-Yno-adapted-args",
        "-Ywarn-numeric-widen",
        "-Xlint:-unused",         // https://stackoverflow.com/a/43965697/4032515
        //"-Ywarn-unused:locals",
        "-Ywarn-value-discard",
        "-deprecation:false", "-Xcheckinit", "-unchecked", "-feature", "-language:_"),
  scalacOptions += "-Ypartial-unification",
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4"),
  scalacOptions in Test               ++= Seq("-Yrangepos"),
  scalacOptions in (Compile, doc)     ++= Seq("-feature", "-language:_"),
  scalacOptions in (Compile, console) := Seq("-Yrangepos", "-feature", "-language:_"),
  scalacOptions in (Test, console)    := Seq("-Yrangepos", "-feature", "-language:_")
)

lazy val testingSettings = Seq(
  initialCommands in console in test := "import org.specs2._",
  logBuffered := false,
  cancelable in Global := true,
  testFrameworks := Seq(TestFramework("org.specs2.runner.Specs2Framework")),
  testOptions := Seq(Tests.Filter(s =>
    (Seq(".guide.").exists(s.contains) || Seq("Spec", "Guide", "Website").exists(s.endsWith)) &&
      Seq("Specification", "FeaturesSpec").forall(n => !s.endsWith(n))))
)

lazy val testingJvmSettings =
  Seq(javaOptions ++= Seq("-Xmx3G", "-Xss4M"),
      fork in Test := true)

/**
 * DOCUMENTATION
 */
lazy val siteSettings = ghpages.settings ++ SbtSite.site.settings ++
  Seq(
    siteSourceDirectory := target.value / "specs2-reports" / "site",
    // copy the api files to a versioned directory
    siteMappings ++= { (mappings in packageDoc in Compile).value.map { case (f, d) => (f, s"api/SPECS2-${version.value}/$d") } },
    includeFilter in makeSite := AllPassFilter,
    // override the synchLocal task to avoid removing the existing files
    synchLocal := {
      val betterMappings = privateMappings.value map { case (file, target) => (file, updatedRepository.value / target) }
      IO.copy(betterMappings)
      updatedRepository.value
    },
    gitRemoteRepo := "git@github.com:etorreborre/specs2.git"
  )

/**
 * UTILITIES
 */
def executeAggregateTask(task: TaskKey[_], info: String) = (st: State) => {
  st.log.info(info)
  val extracted = Project.extract(st)
  val ref: ProjectRef = extracted.get(thisProjectRef)
  extracted.runAggregated(task in ref, st)
}

def executeTask(task: TaskKey[_], info: String) = (st: State) => {
  st.log.info(info)
  val extracted = Project.extract(st)
  val ref: ProjectRef = extracted.get(thisProjectRef)
  extracted.runTask(task in ref, st)._1
}

def executeTask(task: TaskKey[_], info: String, configuration: Configuration) = (st: State) => {
  st.log.info(info)
  val extracted = Project.extract(st)
  val ref: ProjectRef = extracted.get(thisProjectRef)
  extracted.runTask(task in configuration, st)._1
}
