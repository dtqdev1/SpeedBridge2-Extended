plugins {
    id("java")
}

group = "io.tofpu.speedbridge2"
version = "3.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

    maven("https://jitpack.io")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("com.sk89q:worldedit:6.0.0-SNAPSHOT")
    compileOnly("org.immutables:value:2.10.1")
    annotationProcessor("org.immutables:value:2.10.1")

    implementation("com.github.tofpu.MultiWorldEdit:multiworldedit-api:8930fd3caa")
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.github.tofpu.toolbar:toolbar-api:3793d5f149")
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.3.0-M2")
    implementation("com.github.cryptomorin:XSeries:8.7.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}