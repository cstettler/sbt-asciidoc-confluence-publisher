
lazy val root = (project in file(".")).enablePlugins(AsciidocConfluencePublisherPlugin).settings(
  rootConfluenceUrl := "http://localhost:8090",
  spaceKey := "CPI",
  ancestorId := "327706",
  username := "confluence-publisher-it",
  password := "1234"
)

TaskKey[Unit]("check") := {
  println("Publishing OK")
}
