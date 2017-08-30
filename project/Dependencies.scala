import sbt._
import Keys._

object Dependencies {

  lazy val version = new {
      val scalaTest       = "3.0.0"
      val scalaCheck      = "1.13.4"
  }

  lazy val libs = new  {
      val test  = "org.scalatest" %% "scalatest" % version.scalaTest % Test
      val check = "org.scalacheck" %% "scalacheck" % version.scalaCheck % Test
      val scalapb = "com.trueaccord.scalapb" %% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion % "protobuf"

  }

  lazy val grpcLibs: Seq[ModuleID] = Seq(
    "io.grpc" % "grpc-netty" % com.trueaccord.scalapb.compiler.Version.grpcJavaVersion,
    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % com.trueaccord.scalapb.compiler.Version.scalapbVersion
  )

}
