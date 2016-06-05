/** Project */
name := "fpinscalabe"

organization := "org.specs2"

scalaVersion := "2.11.8"

/** Shell */
shellPrompt := { state => "fpinscalabe> " }

/** Dependencies */
resolvers ++= 
    Seq(Resolver.sonatypeRepo("releases"), 
        Resolver.sonatypeRepo("snapshots"),
        Resolver.typesafeRepo("releases")
        //"scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")
  

libraryDependencies <++= version { version =>
  Seq(
    "org.specs2"      %% "specs2-core"         ,
    "org.specs2"      %% "specs2-matcher-extra",
    "org.specs2"      %% "specs2-gwt"          ,
    "org.specs2"      %% "specs2-html"         ,
    "org.specs2"      %% "specs2-form"         ,
    "org.specs2"      %% "specs2-scalacheck"   ,
    "org.specs2"      %% "specs2-mock"         ,
    "org.specs2"      %% "specs2-junit"        
  ).map(_ % version) ++ 
  Seq(
    "org.scalaz"      %% "scalaz-core" % "7.2.3",
    "com.chuusai"     %% "shapeless"   % "2.3.1")
}

scalacOptions ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature", "-language:_")

logBuffered := false

/** Console */
initialCommands in console in Test := "import org.specs2._"

