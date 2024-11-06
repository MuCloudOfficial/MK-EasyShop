plugins {
    kotlin("jvm") version "2.0.21"
}

group = "me.mucloud"
version = "3.1.0.0"

val targetJavaVersion = 17

kotlin {
    jvmToolchain(targetJavaVersion)
}