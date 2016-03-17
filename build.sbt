sbtPlugin := true

organization := "com.bumnetworks"

name := "sbt-out-of-tree"

version := "0.0.1"

publishTo <<= (version) {
  v =>
    val repo = file(".") / ".." / "repo"
    Some(Resolver.file("repo",
      if (v.trim.endsWith("SNAPSHOT")) repo / "snapshots"
      else repo / "releases"))
}
