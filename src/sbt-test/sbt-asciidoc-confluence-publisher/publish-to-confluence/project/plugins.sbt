resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

addSbtPlugin("com.github.cstettler" % "sbt-asciidoc-confluence-publisher" % System.getProperty("plugin.version"))
