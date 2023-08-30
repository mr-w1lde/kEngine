rootProject.name = "kEngine"
include(
    ":launcherDarwin",
    ":engine:common",
    ":engine:system",
    ":engine:render",
    ":engine:input",

    ":gameMain"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val kotlinVersion: String by settings
    val shadowJarVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.github.johnrengelman.shadow") version shadowJarVersion

        id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
