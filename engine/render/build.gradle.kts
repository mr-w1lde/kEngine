plugins {
    id("com.github.johnrengelman.shadow")
    java
}

dependencies {
    compileOnly(projects.engine.common)

    compileOnly("org.lwjgl:lwjgl-opengl:${project.extra["lwjglVersion"]}")
}
