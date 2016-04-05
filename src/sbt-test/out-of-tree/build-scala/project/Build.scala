import sbt._, sbt.Keys._
import outOfTree._
import OutOfTreePlugin.autoImport._

object Local {
  lazy val baseSettings = Defaults.defaultSettings ++ Seq(
    scalaVersion := "2.11.8",
    version := "0.0.1"
  )
  lazy val outOfTreeSettings = OutOfTreePlugin.projectSettings ++ Seq(
    outOfTreeTmp := java.nio.file.Paths.get("/tmp/tmp3")
  )
}

object Erect extends Build {
  import Local._
  lazy val project = Project(
    id = "build-scala",
    base = file("."),
    settings = baseSettings ++ outOfTreeSettings
  )
}
