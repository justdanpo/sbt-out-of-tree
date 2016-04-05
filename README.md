## sbt-out-of-tree

This SBT plugin enables placement of compilation products outside of
the source directory tree.

## Why?

By default SBT uses `target` directories within each (sub)project as its own
private scratch area. This isn't generally a nuisance, because people happily
place `target/` in `.gitignore` and move on.

There are cases, however, when it's advantageous to keep build products in a
different directory tree from the source one. The process of compiling Scala
code involes many IO operations against the `target` directories, resulting in
longer than necessary build times when the source is located on a slow (i.e.
remote) file system.

A specific example of this situation is VirtualBox's shared folders:

* SBT runs inside a (possibly Vagrant-managed) VirtualBox instance
* the working copy is in a VirtualBox shared folder located on the host system

By having SBT place its build products outside the source tree (i.e. in the
`/tmp` file system inside VirtualBox) a 40%+ improvement in compilation time
has been observed when the host system is Windows 7. YMMV.

## How to use the plugin?

### Project-specific

Add this to your `plugins.sbt`:

```scala
resolvers += "bumnetworks" at "http://repo.bumnetworks.com/releases/"

addSbtPlugin("com.bumnetworks" % "sbt-out-of-tree" % "0.0.2")
```

No further action is required on your part: this project's build products will
automatically go into `/tmp`. Run `show target` using the SBT console to find
out where that is.

### User-specific

The same two lines can be placed in `~/.sbt/0.13/plugins/build.sbt` to enable
out-of-tree builds for all projects built by the user account. Note that this
will also affect SBT's own `project/target` build output.

## Configuration

The plugin will auto-import its keys: `outOfTreeRoot` and `outOfTreeTmp`.

### `outOfTreeRoot`

This setting configures the parent directory which will contain `target/`
directories. By default, it looks like this:

* project location: `/home/foo/Projects/quux`
* `outOfTreeRoot`: `${outOfTreeTmp}/sbt-out-of-tree/home/foo/Projects/quux`
* classes directory: `${outOfTreeTmp}/sbt-out-of-tree/home/foo/Projects/quux/target/scala-x.yy/classes`

The out-of-tree output directory is rooted to a temporary file system
configured by the next setting.

### `outOfTreeTmp`

You can override the temporary file system by modifying the `outOfTreeTmp`
setting. It defaults to the `java.io.tmpdir` system property.
