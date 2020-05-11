name := "FamilyTree"

version := "0.1"

scalaVersion := "2.13.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"
libraryDependencies += "com.typesafe" % "config" % "1.4.0"
parallelExecution in Test := false
