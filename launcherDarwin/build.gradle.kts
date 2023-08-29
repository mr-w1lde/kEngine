plugins {
    id("com.github.johnrengelman.shadow")
    java
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Engine
    implementation(projects.engine.common)


    // Game
    implementation(projects.gameMain)

    // Needed
    implementation("org.reflections:reflections:0.10.2")
}

tasks {
    application {
        mainClass = "launcher.MainKt"
    }
    runShadow {
        jvmArgs?.plusAssign(
            listOf("-XstartOnFirstThread")
        )
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
