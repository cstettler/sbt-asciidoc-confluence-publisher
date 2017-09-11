
ScriptedPlugin.scriptedSettings
ScriptedPlugin.scriptedLaunchOpts := {
  ScriptedPlugin.scriptedLaunchOpts.value ++
    Seq("-Dplugin.version=" + version.value)
}

sbtPlugin := true
scalaVersion := "2.10.4"

organization := "com.github.cstettler"
name := "sbt-asciidoc-confluence-publisher"
version := "0.1.0-SNAPSHOT"

libraryDependencies += "org.sahli.asciidoc.confluence.publisher" % "asciidoc-confluence-publisher-converter" % "0.2.0"
libraryDependencies += "org.sahli.asciidoc.confluence.publisher" % "asciidoc-confluence-publisher-client" % "0.2.0"
