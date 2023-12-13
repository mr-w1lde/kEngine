plugins {
    id("com.github.johnrengelman.shadow")
    java
}

dependencies {
    compileOnly(projects.engine.common)

    compileOnly("org.lwjgl:lwjgl-opengl:${project.extra["lwjglVersion"]}")
    compileOnly("io.github.spair:imgui-java-lwjgl3:${project.extra["imguiVersion"]}")
    compileOnly("io.github.spair:imgui-java-binding:${project.extra["imguiVersion"]}")
}
