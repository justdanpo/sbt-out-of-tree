package outOfTree

import sbt._, sbt.Keys._
import java.nio.file.{ Path, Paths }

object OutOfTreePlugin extends AutoPlugin {
  object autoImport {
    lazy val outOfTreeTmp = settingKey[Path]("Temporary directory for out-of-tree builds (defaults to java.io.tmpdir)")
    lazy val outOfTreeRoot = settingKey[Path]("Location of out-of-tree build root")
  }

  import autoImport._

  override def requires = sbt.plugins.JvmPlugin
  override def trigger = allRequirements

  private def settings = Seq(
    outOfTreeTmp := Paths.get(System.getProperty("java.io.tmpdir")),
    outOfTreeRoot <<= (thisProject, outOfTreeTmp) { (p, tmp) =>
      val base = p.base.toPath.toAbsolutePath.normalize
      tmp.resolve(s"sbt-out-of-tree").resolve(base.subpath(0, base.getNameCount))
    },
    target <<= outOfTreeRoot { _.resolve("target").toFile }
  )

  override lazy val projectSettings = settings
}
