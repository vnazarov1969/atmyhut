
name := """athut_server"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

routesGenerator := StaticRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
//  javaEbean,
//  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "jquery-validation" % "1.13.1" exclude("org.webjars", "jquery"),
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4"
)
//libraryDependencies += "play-logback_2.11"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)


fork in run := false
