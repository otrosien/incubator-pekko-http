/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

resolvers += Classpaths.sbtPluginReleases
resolvers += Classpaths.typesafeReleases
resolvers ++= Resolver.sonatypeOssRepos("releases") // to more quickly obtain paradox right after release

// need this to resolve https://jcenter.bintray.com/org/jenkins-ci/jenkins/1.26/
// which is used by plugin "org.kohsuke" % "github-api" % "1.68"
resolvers += Resolver.jcenterRepo

addSbtPlugin("com.typesafe.sbt" % "sbt-multi-jvm" % "0.4.0")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.1.0")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.1.1")
addSbtPlugin("com.github.sbt" % "sbt-unidoc" % "0.5.0")
addSbtPlugin("com.thoughtworks.sbt-api-mappings" % "sbt-api-mappings" % "3.0.2")
addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.4.3")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1") // for advanced PR validation features
addSbtPlugin("io.spray" % "sbt-boilerplate" % "0.6.1")
addSbtPlugin("com.lightbend.sbt" % "sbt-bill-of-materials" % "1.0.2")
addSbtPlugin("com.github.sbt" % "sbt-license-report" % "1.5.0")

// allow access to snapshots for pekko-sbt-paradox
resolvers += Resolver.ApacheMavenSnapshotsRepo
updateOptions := updateOptions.value.withLatestSnapshots(false)

// We have to deliberately use older versions of sbt-paradox because current Pekko sbt build
// only loads on JDK 1.8 so we need to bring in older versions of parboiled which support JDK 1.8
addSbtPlugin(("org.apache.pekko" % "pekko-sbt-paradox" % "0.0.0+43-d9643c78-SNAPSHOT").excludeAll(
  "com.lightbend.paradox", "sbt-paradox",
  "com.lightbend.paradox" % "sbt-paradox-apidoc",
  "com.lightbend.paradox" % "sbt-paradox-project-info"))
addSbtPlugin(("com.lightbend.paradox" % "sbt-paradox" % "0.9.2").force())
addSbtPlugin(("com.lightbend.paradox" % "sbt-paradox-apidoc" % "0.10.1").force())
addSbtPlugin(("com.lightbend.paradox" % "sbt-paradox-project-info" % "2.0.0").force())

addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.9.0")
addSbtPlugin("net.bzzt" % "sbt-reproducible-builds" % "0.30")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.11.0")
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.1.1")
addSbtPlugin("org.mdedetrich" % "sbt-apache-sonatype" % "0.1.10")
addSbtPlugin("com.github.pjfanning" % "sbt-source-dist" % "0.1.8")
addSbtPlugin("net.virtual-void" % "sbt-hackers-digest" % "0.1.2")
addSbtPlugin("com.lightbend.sbt" % "sbt-java-formatter" % "0.7.0")

// used in ValidatePullRequest to check github PR comments whether to build all subprojects
libraryDependencies += "org.kohsuke" % "github-api" % "1.306"

// used for @unidoc directive
libraryDependencies += "io.github.lukehutch" % "fast-classpath-scanner" % "3.1.15"
