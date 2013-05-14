import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "RSSFeed"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "org.apache.derby" % "derby" % "10.10.1.1",
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
