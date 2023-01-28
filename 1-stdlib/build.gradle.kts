plugins {
    kotlin("jvm")
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

tasks.test {
    useJUnitPlatform()
}
