plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"

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
    mainClass.set("training.AppKt")
}
