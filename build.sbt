import Dependencies._

lazy val proto3 = (project in file("proto3")).
  settings(Settings.settings: _*).
  settings(Settings.proto3Settings: _*).
  settings(Settings.scalapbSettings).
  configs(Test)