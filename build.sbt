lazy val commonSettings = Seq(
  scalaVersion := "2.11.12",
  dexMaxHeap := "4g",

  organization := "com.github.shadowsocks",

  platformTarget := "android-27",

  compileOrder := CompileOrder.JavaThenScala,
  javacOptions ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil,
  scalacOptions ++= "-target:jvm-1.7" :: "-Xexperimental" :: Nil,
  ndkArgs := "-j" :: java.lang.Runtime.getRuntime.availableProcessors.toString :: Nil,
  ndkAbiFilter := Seq("armeabi-v7a", "arm64-v8a", "x86"),

  proguardVersion := "5.3.3",
  proguardCache := Seq(),

  shrinkResources := true,
  typedResources := false,

  resConfigs := Seq("fa", "ja", "ko", "ru", "zh-rCN", "zh-rTW"),

  resolvers += "google" at "https://maven.google.com",
  packagingOptions in Android := PackagingOptions(excludes = Seq(
				"META-INF/com.android.support_preference-v7.version",
				"META-INF/maven/com.j256.ormlite/ormlite-android/pom.xml",
				"META-INF/maven/com.google.zxing/core/pom.xml",
				"META-INF/com.android.support_support-core-utils.version",
				"META-INF/maven/com.j256.ormlite/ormlite-core/pom.xml",
				"META-INF/com.android.support_animated-vector-drawable.version",
				"META-INF/maven/net.glxn.qrgen/core/pom.xml",
				"META-INF/maven/dnsjava/dnsjava/pom.xml",
				"META-INF/maven/com.j256.ormlite/ormlite-android/pom.properties",
				"META-INF/com.android.support_support-media-compat.version",
				"META-INF/com.android.support_gridlayout-v7.version",
				"META-INF/com.android.support_support-compat.version",
				"META-INF/maven/net.glxn.qrgen/android/pom.properties",
				"META-INF/com.android.support_appcompat-v7.version",
				"META-INF/com.android.support_customtabs.version",
				"META-INF/com.android.support_design.version",
				"META-INF/maven/com.j256.ormlite/ormlite-core/pom.properties",
				"META-INF/maven/com.google.zxing/core/pom.properties",
				"META-INF/com.android.support_support-core-ui.version",
				"META-INF/com.android.support_preference-v14.version",
				"META-INF/maven/net.glxn.qrgen/android/pom.xml",
				"META-INF/maven/dnsjava/dnsjava/pom.properties",
				"META-INF/maven/net.glxn.qrgen/core/pom.properties",
				"META-INF/com.android.support_support-fragment.version",
				"META-INF/com.android.support_support-v4.version",
				"META-INF/services/sun.net.spi.nameservice.NameServiceDescriptor",
				"META-INF/com.android.support_support-vector-drawable.version",
				"META-INF/com.android.support_recyclerview-v7.version",
				"META-INF/com.android.support_transition.version"))
)

val supportLibsVersion = "27.0.1"
lazy val root = Project(id = "shadowsocks-android", base = file("."))
  .settings(commonSettings)
  .aggregate(plugin, mobile)

install in Android := (install in (mobile, Android)).value
run in Android := (run in (mobile, Android)).evaluated

lazy val plugin = project
  .settings(commonSettings)
  .settings(
    libraryDependencies += "com.android.support" % "preference-v14" % supportLibsVersion
  )

lazy val mobile = project
  .settings(commonSettings)
  .settings(
    libraryDependencies ++=
      "com.android.support" % "customtabs" % supportLibsVersion ::
      "com.android.support" % "design" % supportLibsVersion ::
      "com.android.support" % "gridlayout-v7" % supportLibsVersion ::
      Nil
  )
  .dependsOn(plugin)
