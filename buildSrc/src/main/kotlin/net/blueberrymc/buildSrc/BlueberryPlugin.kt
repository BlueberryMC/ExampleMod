package net.blueberrymc.buildSrc

import net.blueberrymc.buildSrc.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleDependency
import java.io.File

class BlueberryPlugin : Plugin<Project> {
    companion object {
        internal lateinit var configuration: PluginConfig
    }

    override fun apply(project: Project) {
        configuration = PluginConfig(project)
        File("temp").mkdir()
        project.task("downloadInstallerJar", DownloadInstallerJarTask())
        project.task("unzipInstallerJar", UnzipInstallerJarTask())
        project.task("downloadVanillaJar", DownloadVanillaJarTask())
        project.task("patchVanillaJar", PatchVanillaJarTask())
    }
}

fun blueberry(consumer: PluginConfig.() -> Unit) {
    consumer(BlueberryPlugin.configuration)
}

fun Project.blueberry() {
    val lwjglVersion = "3.2.2"
    val config = BlueberryPlugin.configuration
    dependencies.add("compileOnly", "net.blueberrymc:minecraftforge-api:${config.apiVersion.get()}")
    dependencies.add("compileOnly", "net.sf.jopt-simple:jopt-simple:5.0.4")
    dependencies.add("compileOnly", "net.minecrell:terminalconsoleappender:1.2.0")
    dependencies.add("compileOnly", "org.jline:jline-terminal-jansi:3.12.1")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-stb:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-stb:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-stb:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-stb:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-glfw:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-glfw:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-glfw:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-glfw:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-opengl:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-opengl:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-opengl:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-opengl:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-openal:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-openal:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-openal:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-openal:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-tinyfd:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-tinyfd:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-tinyfd:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-tinyfd:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-jemalloc:$lwjglVersion")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-jemalloc:$lwjglVersion:natives-linux")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-jemalloc:$lwjglVersion:natives-macos")
    dependencies.add("compileOnly", "org.lwjgl:lwjgl-jemalloc:$lwjglVersion:natives-windows")
    dependencies.add("compileOnly", "com.github.oshi:oshi-core:5.8.2")
    dependencies.add("compileOnly", "com.mojang:blocklist:1.0.6")
    dependencies.add("compileOnly", "com.mojang:text2speech:1.11.3")
    dependencies.add("compileOnly", "com.mojang:text2speech:1.11.3:natives-linux")
    dependencies.add("compileOnly", "com.mojang:text2speech:1.11.3:natives-windows")
    dependencies.add("compileOnly", "net.java.jutils:jutils:1.0.0")
    dependencies.add("compileOnly", "net.java.dev.jna:jna:5.9.0")
    dependencies.add("compileOnly", "net.java.dev.jna:jna-platform:5.9.0")
    dependencies.add("compileOnly", "com.ibm.icu:icu4j:69.1")
    dependencies.add("compileOnly", "org.apache.commons:commons-lang3:3.12.0")
    dependencies.add("compileOnly", "commons-io:commons-io:2.11.0")
    dependencies.add("compileOnly", "commons-logging:commons-logging:1.2")
    dependencies.add("compileOnly", "org.apache.logging.log4j:log4j-api:2.14.1")
    dependencies.add("compileOnly", "org.apache.logging.log4j:log4j-core:2.14.1")
    dependencies.add("compileOnly", "org.apache.logging.log4j:log4j-slf4j18-impl:2.14.1")
    dependencies.add("compileOnly", "org.slf4j:slf4j-api:1.8.0-beta4")
    dependencies.add("compileOnly", files("temp/patched-${config.minecraftVersion.get()}-${config.apiVersion.get()}.jar"))
    dependencies.add("compileOnly", "net.blueberrymc:blueberry-api:${config.apiVersion.get()}")?.apply {
        exclude("com.github.Vatuu", "discord-rpc")
    }
}

fun Dependency.exclude(group: String, module: String) {
    if (this is ModuleDependency) {
        this.exclude(mapOf("group" to group, "module" to module))
    }
}
