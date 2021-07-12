name := "AkkaHttpRequest"

version := "0.1"

scalaVersion := "2.13.6"

val akkaVersion = "2.5.26"
val akkaHttpVersion = "10.1.11"

scalacOptions ++= Seq(
//  "-encoding", "utf8", // Option and arguments on same line
//  "-Xfatal-warnings",  // New lines for each options
//  "-deprecation",
//  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)

libraryDependencies ++= Seq(
  // akka streams
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  // akka http
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
)
