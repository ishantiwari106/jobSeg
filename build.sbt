name := "JobSeg"

version := "0.1"

scalaVersion := "2.13.4"

val AkkaVersion = "2.5.31"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.26",
  "com.typesafe.akka" %% "akka-http" % "10.1.11",
  "com.typesafe.akka" %% "akka-stream" % "2.5.26",
  "ch.rasc" % "bsoncodec" % "1.0.1",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "2.0.2",
  "org.scalatest" %% "scalatest" % "3.2.2" % "test",
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion
)
