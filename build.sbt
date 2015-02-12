name := "play-tutorial"

version := "1.0"

lazy val `play-tutorial` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws ,
"com.typesafe.play" %% "play-test"  % "2.3.7" )


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
