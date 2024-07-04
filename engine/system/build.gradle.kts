plugins {
    id("com.github.johnrengelman.shadow")
    java
}

dependencies {
    compileOnly(projects.engine.common)
    implementation("org.ini4j:ini4j:0.5.4")
}
