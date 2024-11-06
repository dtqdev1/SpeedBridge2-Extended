plugins {
    id("java")
}

group = "io.tofpu.speedbridge2"
version = "3.0.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")

    group = "io.tofpu.speedbridge2"
    version = "3.0.0-SNAPSHOT"

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }
}