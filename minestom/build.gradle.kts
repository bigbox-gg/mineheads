plugins {
    java
    idea
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

dependencies {
    implementation(project(":api"))

    implementation("com.github.MinestomBrick:SmartInvs:8e89a2e41f")
}

tasks {
    processResources {
        // Apply properties to extension.json
        filesMatching("extension.json") {
            expand(project.properties)
        }
    }

    build {
        dependsOn(shadowJar)
    }

    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("mineheads")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "gg.bigbox.minecraft.plugins.mineheads.minestom.MineHeadsMinestomImpl"))
        }
    }
}