plugins {
    id("com.github.johnrengelman.shadow")
    java
}

dependencies {
    compileOnly(projects.engine.common)
}
