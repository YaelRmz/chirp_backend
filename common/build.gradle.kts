plugins {
    id("java-library")
    id("chirp.kotlin-common")
}

group = "com.yrmz"
version = "unspecified"

repositories {
    maven { url = uri("https://repo.spring.io/snapshot") }
    mavenCentral()
}
