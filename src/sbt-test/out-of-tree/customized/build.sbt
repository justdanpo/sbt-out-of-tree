import outOfTree._

OutOfTreePlugin.projectSettings ++ Seq(
  outOfTreeTmp := java.nio.file.Paths.get("/tmp/tmp2")
)

name := "customized"

version := "0.0.1"

scalaVersion := "2.11.8"
