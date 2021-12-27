repositories {
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(project(":api"))

    implementation("com.github.Minestom:Minestom:master-SNAPSHOT")
}