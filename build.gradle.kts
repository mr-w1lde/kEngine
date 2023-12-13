plugins {
    kotlin("jvm")
}

allprojects {
    version = "0.0.1"
    apply(plugin = "kotlin")

    repositories {
        // Use Maven Central for resolving dependencies.
        mavenCentral()
    }

    afterEvaluate {
        dependencies {
            implementation(kotlin("reflect"))
            implementation(platform(kotlin("bom")))
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${project.extra["kotlinVersion"]}")

            implementation("ch.qos.logback:logback-classic:1.4.11")
            implementation("org.reflections:reflections:0.10.2")
            implementation("dev.romainguy:kotlin-math:1.5.3")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3")

            compileOnly("org.lwjgl:lwjgl-glfw:${project.extra["lwjglVersion"]}")
        }

    }
}
