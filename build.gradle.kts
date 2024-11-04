plugins {
    kotlin("jvm") version "2.0.21"
    id("com.gradleup.shadow") version "8.3.4"
}

group = "me.mucloud"

val targetJavaVersion = 17
kotlin {
    jvmToolchain(targetJavaVersion)
}
