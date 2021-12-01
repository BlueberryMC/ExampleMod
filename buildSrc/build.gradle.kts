plugins {
    kotlin("jvm") version "1.6.0"
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("io.sigpipe:jbsdiff:1.0")
}
