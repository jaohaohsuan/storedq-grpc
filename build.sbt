import Dependencies._

lazy val proto3 = (project in file("proto3")).
  settings(Settings.settings: _*).
  settings(Settings.assemblySettings: _*).
  settings(Settings.scalapbSettings).
  configs(Test)