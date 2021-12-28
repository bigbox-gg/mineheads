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
    version = "1.0"

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    dependencies {
        // https://mvnrepository.com/artifact/com.google.code.gson/gson
        implementation("com.google.code.gson:gson:2.8.9")
    }
}