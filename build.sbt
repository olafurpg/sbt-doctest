val versions = new {
  val ScalaTest  = "3.0.5"
  val ScalaCheck = "1.14.0"
  val Specs2     = "4.3.2"
  val utest      = "0.6.4"
  val Minitest   = "2.1.1"
  val CommonsIO  = "2.6"
  val Lang3      = "3.7"
  val ScalaMeta  = "4.0.0-RC1"
}

lazy val root = (project in file(".")).settings(
  scalaVersion := "2.12.6",
  crossSbtVersions := Vector("1.1.6"),
  organization := "com.github.tkawachi",
  name := "sbt-doctest",
  licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
  scmInfo := Some(ScmInfo(
    url("https://github.com/tkawachi/sbt-doctest/"),
    "scm:git:github.com:tkawachi/sbt-doctest.git"
  )),
  javacOptions ++= Seq(
    "-encoding", "UTF-8"),
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint:-unused,_"
  ),
  libraryDependencies ++= Seq(
    "commons-io"         %  "commons-io"          % versions.CommonsIO,
    "org.apache.commons" %  "commons-lang3"       % versions.Lang3,
    "org.scalameta"      %% "scalameta"           % versions.ScalaMeta,
    "org.scalameta"      %% "contrib"             % versions.ScalaMeta,
    "com.lihaoyi"        %% "utest"               % versions.utest        % Provided,
    "org.scalatest"      %% "scalatest"           % versions.ScalaTest    % Provided,
    "org.scalacheck"     %% "scalacheck"          % versions.ScalaCheck   % Provided,
    "org.specs2"         %% "specs2-core"         % versions.Specs2       % Provided,
    "org.specs2"         %% "specs2-scalacheck"   % versions.Specs2       % Provided,
    "io.monix"           %% "minitest"            % versions.Minitest     % Provided,
    "io.monix"           %% "minitest-laws"       % versions.Minitest     % Provided
  ),

  // allows this plugin to eat its own dog food
  inConfig(Compile)(
    Seq(
      libraryDependencies ++= Seq(
        "com.lihaoyi"        %% "utest"               % versions.utest        % Test,
        "org.scalatest"      %% "scalatest"           % versions.ScalaTest    % Test,
        "org.scalacheck"     %% "scalacheck"          % versions.ScalaCheck   % Test,
        "org.specs2"         %% "specs2-core"         % versions.Specs2       % Test,
        "org.specs2"         %% "specs2-scalacheck"   % versions.Specs2       % Test,
        "io.monix"           %% "minitest"            % versions.Minitest     % Test,
        "io.monix"           %% "minitest-laws"       % versions.Minitest     % Test
      )
    )
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
).enablePlugins(SbtPlugin)
