plugins {
    id("java-library")
    id("chirp.kotlin-common")
}

group = "com.yrmz"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    api(libs.kotlin.reflect)
    api(libs.jackson.module.kotlin)

    implementation(libs.spring.boot.starter.amqp)
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.+")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
