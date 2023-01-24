plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    mainClass.set("training.MainKt")
}
