import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.flxkbr"
ThisBuild / organizationName := "flxkbr"
ThisBuild / test in assembly := {}

lazy val root = (project in file("."))
  .settings(
    name := "scala-refresh",
    libraryDependencies ++= Seq(
      scalactic,
      scalaTest % Test,
      cats),
    scalacOptions ++= Seq(
      "-Ypartial-unification",
      "-language:higherKinds"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
