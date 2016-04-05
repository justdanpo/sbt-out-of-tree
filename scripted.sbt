ScriptedPlugin.scriptedSettings

scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx512M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false
