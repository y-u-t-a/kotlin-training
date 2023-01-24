plugins {
    kotlin("jvm") version "1.8.0"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("training.MainKt")
}
