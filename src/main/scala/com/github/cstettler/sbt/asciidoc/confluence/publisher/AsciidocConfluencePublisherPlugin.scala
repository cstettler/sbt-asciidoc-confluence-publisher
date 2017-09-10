package com.github.cstettler.sbt.asciidoc.confluence.publisher

import org.sahli.asciidoc.confluence.publisher.client.ConfluencePublisher
import org.sahli.asciidoc.confluence.publisher.client.http.ConfluenceRestClient
import org.sahli.asciidoc.confluence.publisher.converter.{AsciidocConfluenceConverter, FolderBasedAsciidocPagesStructureProvider}
import sbt.Keys._
import sbt.{AutoPlugin, Def, _}

object AsciidocConfluencePublisherPlugin extends AutoPlugin {

  object autoImport {
    val asciidocRootFolder: SettingKey[File] = SettingKey[File]("documentation root folder")
    val buildFolder: SettingKey[File] = SettingKey[File]("build folder")
    val rootConfluenceUrl: SettingKey[String] = SettingKey[String]("root url to confluence")
    val spaceKey: SettingKey[String] = SettingKey[String]("key of confluence space")
    val ancestorId: SettingKey[String] = SettingKey[String]("id of ancestor")
    val username: SettingKey[String] = SettingKey[String]("username")
    val password: SettingKey[String] = SettingKey[String]("password")
    val publishToConfluence: TaskKey[Unit] = TaskKey[Unit]("publishToConfluence", "publish documentation to Confluence")
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    asciidocRootFolder := baseDirectory.value / "etc/docs",
    buildFolder := target.value / "asciidoc-confluence-publisher",
    publishToConfluence <<= publishToConfluenceTask
  )

  private def publishToConfluenceTask = (asciidocRootFolder, buildFolder, rootConfluenceUrl, spaceKey, ancestorId, username, password) map { (_asciidocRootFolder, _buildFolder, _rootConfluenceUrl, _spaceKey, _ancestorId, _username, _password) =>
    println(s"publishing documentation from ${_asciidocRootFolder} to confluence at ${_rootConfluenceUrl} ...")

    val currentContextClassLoader = Thread.currentThread().getContextClassLoader

    try {
      Thread.currentThread().setContextClassLoader(getClass.getClassLoader)

      val asciidocPagesStructureProvider = new FolderBasedAsciidocPagesStructureProvider(_asciidocRootFolder.toPath)
      val asciidocConfluenceConverter = new AsciidocConfluenceConverter(_spaceKey, _ancestorId)
      val confluencePublisherMetadata = asciidocConfluenceConverter.convert(asciidocPagesStructureProvider, _buildFolder.toPath)

      val confluenceRestClient = new ConfluenceRestClient(_rootConfluenceUrl, _username, _password)
      val confluencePublisher = new ConfluencePublisher(confluencePublisherMetadata, confluenceRestClient)
      confluencePublisher.publish()
    } finally {
      Thread.currentThread().setContextClassLoader(currentContextClassLoader)
    }
  }

}