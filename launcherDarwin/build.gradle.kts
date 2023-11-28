plugins {
    id("com.github.johnrengelman.shadow")
    java
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val lwjglPlatform = "natives-macos-arm64"

dependencies {
    // Engine
    implementation(projects.engine.common)
    implementation(projects.engine.render)
    implementation(projects.engine.system)
    implementation(projects.engine.input)

    // Game
    implementation(projects.gameMain)

    // SDK
    implementation(platform("org.lwjgl:lwjgl-bom:${project.extra["lwjglVersion"]}"))
    implementation("org.lwjgl:lwjgl-glfw:${project.extra["lwjglVersion"]}")
    implementation("org.lwjgl:lwjgl-opengl:${project.extra["lwjglVersion"]}")

    // Runtime
    runtimeOnly("org.lwjgl:lwjgl::$lwjglPlatform")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglPlatform")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglPlatform")
}

tasks {
    application {
        mainClass = "launcher.LauncherKt"
    }
    runShadow {
        val args = listOf("-XstartOnFirstThread", "-Dorg.lwjgl.util.Debug=true")
        if (jvmArgs != null) {
            jvmArgs!!.plusAssign(args)
        } else {
            jvmArgs = args
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
