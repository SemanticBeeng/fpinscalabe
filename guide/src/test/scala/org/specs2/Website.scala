package org.specs2

import org.specs2.control._
import org.specs2.html._
import org.specs2.io._
import org.specs2.main._
import org.specs2.runner._
import org.specs2.fp.syntax._
import org.specs2.specification.core.Env
import org.specs2.ugbase.{VersionTag, UserGuide}

import scalaz.Scalaz._
import scalaz._
import ExecuteActions._

class Website(env: Env) extends Specification with ugbase.Specs2Variables with ugbase.Specs2Tags { def is = s2"""

 create the website    $createWebsite
 create the user guide $createUserGuide

"""

  val outputDir = "target" / "specs2-reports" / "site"
  val versionDirName = FileName.unsafe("SPECS2-"+VERSION)

  def createWebsite = {
    val env = Env()
    val fs = env.fileSystem
    val pages = List("index", "quickstart", "learn", "project", "nav").map(_ +".html").map(resource)
    val directories = List("css", "fonts", "images", "javascript").map(resourceDir)
    val vars = variables(env)
    val siteOutputDir = outputDir / "website" / versionDirName

    val action =
      pages.map { page =>
        for {
          _               <- directories.map(d => fs.copyDir(d, siteOutputDir / d.name)).sequence
          template        <- fs.readFile(page)
          replacedVersion <- HtmlTemplate.runTemplate(template, vars)
          _               <-
            fs.writeFile(siteOutputDir | page.name, replacedVersion) >> {
              // copy the index page at the root of the site
              // it will then re-direct to a specific version
              if (page.path.contains("index.html") && isOfficial(VERSION)) fs.writeFile(outputDir | page.name, replacedVersion)
              else Operations.ok(())
            }
        } yield ()
      }.sequence >> writeVersionsFile(fs, siteOutputDir, vars("GUIDE_DIR"), vars("API_DIR"))

    runOperation(action) must beRight
  }

  def createUserGuide = {
    val guideOutputDir = outputDir / "guide" / versionDirName
    val env = Env(arguments = Arguments.split(s"all html console html.search html.toc html.nostats html.outdir ${guideOutputDir.dirPath}"))

    val action =
      env.fileSystem.copyFile(guideOutputDir / "css")(resource("css/specs2-user.css")).toAction >>
      ClassRunner.report(env)(UserGuide)

    try     runAction(action)(env.executionEnv) must beRight
    finally env.shutdown
  }

  def writeVersionsFile(fs: FileSystem, siteOutputDir: DirectoryPath, guideDir: String, apiDir: String): Operation[Unit] =
    publishedTags >>= (tags => fs.writeFile(siteOutputDir / "javascript" | "versions.js", versionsJavaScript(tags, guideDir, apiDir)))

  def versionsJavaScript(tags: List[VersionTag], guideDir: String, apiDir: String): String = {
    def makeVersionVar(name: String, file: String) =
      s"""|var ${name}Versions = [
          | ${tags.map(_.render).map(tag => s"""{id:"../../$name/$tag/$file", text:"${tag.replace("SPECS2-", "")}"}""").mkString(",\n")}
          |];""".stripMargin

    makeVersionVar("guide", "org.specs2.guide.UserGuide.html")+"\n "+
    makeVersionVar("api",   "index.html")
  }


  def variables(env: Env): Map[String, String] =
    specs2Variables.map { case (key, value) =>
      (key, env.arguments.commandLine.valueOr(key, value))
    }

  def resource(name: String): FilePath =
    FilePath.unsafe(getClass.getClassLoader.getResource(name).toURI)


  def resourceDir(name: String): DirectoryPath =
    DirectoryPath.unsafe(getClass.getClassLoader.getResource(name).toURI)
}
