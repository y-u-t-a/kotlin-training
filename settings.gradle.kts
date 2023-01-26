rootProject.name = "kotlin-training"
include(
    "1-stdlib",
    "2-serialization",
)

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
    }
}
