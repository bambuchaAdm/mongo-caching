import play.core.PlayVersion
import sbt.Keys._
import sbt._

val libName = "mongo-caching"

val compileDependencies = Seq(
  "com.typesafe.play" %% "play"               % PlayVersion.current % "provided",
  "uk.gov.hmrc"       %% "play-reactivemongo" % "6.2.0",
  "uk.gov.hmrc"       %% "time"               % "3.0.0",
  "uk.gov.hmrc"       %% "http-core"          % "0.6.0"
)

val testDependencies = Seq(
  "uk.gov.hmrc"       %% "reactivemongo-test" % "2.0.0"             % "test",
  "org.scalatest"     %% "scalatest"          % "2.2.4"             % "test",
  "com.typesafe.play" %% "play-test"          % PlayVersion.current % "test",
  "org.pegdown"       % "pegdown"             % "1.4.2"             % "test"
)

lazy val mongoCache = Project(libName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
    .settings(
      majorVersion := 5,
      makePublicallyAvailableOnBintray := true,
      scalaVersion := "2.11.7",
      libraryDependencies ++= compileDependencies ++ testDependencies,
      evictionWarningOptions in update := EvictionWarningOptions.default.withWarnScalaVersionEviction(false),
      resolvers := Seq(
        "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/",
        Resolver.bintrayRepo("hmrc", "releases"),
        Resolver.jcenterRepo
      ),
      crossScalaVersions := Seq("2.11.7"),
      ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
    )