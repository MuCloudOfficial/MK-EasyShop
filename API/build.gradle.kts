plugins {
    kotlin("jvm") version "2.0.21"
}

group = "me.mucloud"
version = "3.1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}