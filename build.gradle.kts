import net.blueberrymc.buildSrc.blueberry

plugins {
    java
}

apply<net.blueberrymc.buildSrc.BlueberryPlugin>()

group = "net.blueberrymc.example"
version = "0.0.1"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

blueberry {
    minecraftVersion.set("1.18")
    apiVersion.set("0.0.18")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://repo2.acrylicstyle.xyz/") }
}

dependencies {
    blueberry()
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

/*
tasks.getByName("prepareKotlinBuildScriptModel") {
    dependsOn("patchVanillaJar")
}

tasks.getByName("compileJava") {
    dependsOn("patchVanillaJar")
}
*/

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
