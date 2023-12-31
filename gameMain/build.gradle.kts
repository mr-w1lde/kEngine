plugins {
    id("com.github.johnrengelman.shadow")
    java
}

dependencies {
    implementation(project(mapOf("path" to ":engine:system")))
    compileOnly(projects.engine.common)
    compileOnly(projects.engine.system)
}
