import sbt._
import Keys._
import sbtassembly.AssemblyPlugin.autoImport._
import Dependencies.{ libs, grpcLibs }
import sbtprotoc.ProtocPlugin.autoImport.PB

object Settings {


  lazy val settings = Seq(
    organization := "com.grandsys",
    version := "0.0.1",
    scalaVersion := "2.12.3",
    publishMavenStyle := true,
    publishArtifact in Test := false
  )

  lazy val testSettings = Seq(
    fork in Test := false,
    parallelExecution in Test := false
  )

  lazy val itSettings = Defaults.itSettings ++ Seq(
    logBuffered in IntegrationTest := false,
    fork in IntegrationTest := true
  )

  lazy val scalapbSettings = Seq(
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value
    ),
    libraryDependencies ++= Seq(libs.scalapb) ++ grpcLibs
  )

  lazy val assemblySettings = Seq(
    assemblyJarName in assembly := "storedq-grpc-" + version.value + ".jar",
    test in assembly := {},
    target in assembly := file(baseDirectory.value + "/../bin/"),
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(
      includeScala = false,
      includeDependency=true),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs@_*) => MergeStrategy.discard
      case n if n.startsWith("reference.conf") => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )

}
