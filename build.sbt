sbtPlugin := true

organization := "com.bumnetworks"

name := "sbt-out-of-tree"

version := "0.0.3-SNAPSHOT"

publishTo := {
    val repo = file(".") / ".." / "repo"
    Some(Resolver.file("repo",
      if (version.value.trim.endsWith("SNAPSHOT")) repo / "snapshots"
      else repo / "releases"))
}
