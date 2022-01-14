buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("io.freefair.gradle:lombok-plugin:6.3.0")
    }
}

plugins {
    idea
    java
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "io.freefair.lombok")

    group = "gg.bigbox.minecraft.plugins.mineheads"
    version = "0.1"

    repositories {
        mavenCentral()
        maven(url = "https://repo.spongepowered.org/maven")
        maven(url = "https://jitpack.io")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    dependencies {
        // https://mvnrepository.com/artifact/com.google.code.gson/gson
        implementation("com.google.code.gson:gson:2.8.9")

        implementation("com.github.Minestom:Minestom:0366027c5d")
    }
}