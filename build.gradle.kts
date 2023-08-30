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

            compileOnly("org.reflections:reflections:0.10.2")
            compileOnly("org.lwjgl:lwjgl-glfw:${project.extra["lwjglVersion"]}")
        }

    }
}
