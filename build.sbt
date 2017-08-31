lazy val root = (project in file(".")).
  settings(Settings.settings: _*).
  settings(Settings.assemblySettings: _*).
  settings(Settings.scalapbSettings).
  configs(Test)