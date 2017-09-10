
sbtPlugin := true
scalaVersion := "2.10.4"

organization := "com.github.cstettler"
name := "sbt-asciidoc-confluence-publisher"
version := "0.1.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.sahli.asciidoc.confluence.publisher" % "asciidoc-confluence-publisher-converter" % "0.0.0-SNAPSHOT"
libraryDependencies += "org.sahli.asciidoc.confluence.publisher" % "asciidoc-confluence-publisher-client" % "0.0.0-SNAPSHOT"
