import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import groovy.lang.Closure

plugins {
    java
    kotlin("jvm") version "1.3.10"
    id("com.github.johnrengelman.shadow") version "4.0.3"
}

group = "com.abitofhelp"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

///////////////////////////////////////////////////////////////////////
// This task must be added to the build.gradle.kts file
// in order to create a shadowjar containing the required information.
///////////////////////////////////////////////////////////////////////

tasks.withType<ShadowJar> {
    manifest.attributes.apply {

        ///////////////////////////////////////////////////////////
        // The following attributes apply to the archive/jar file
        // that is being created and not the manifest.
        ///////////////////////////////////////////////////////////

        baseName = "simpleshadow"

        // A description about the archive/jar being created;
        // I couldn't find it being applied to an archive/jar.
        description = "A simple app to determine how to configure shadowjar with Kotlin DSL in Gradle."

        // The base name of the archive/jar being created
        // archiveName = "thearchivename"

        // The directory path for the archive/jar being created
        // destinationDir = File("")

        // The extension part of the archive/jar name
        // extension = "JAR"

        // The appendix part of the archive/jar name
        // appendix = "abc"

        // The classifier part of the archive/jar name
        classifier = "all"

        // The version part of the archive/jar name
        version = "1.0.0"

        ///////////////////////////////////////////////////////////
        // The following attributes apply to the mainfest for the
        // archive/jar being created.
        ///////////////////////////////////////////////////////////
        manifest {
            attributes(
                mutableMapOf<String, String>(
                    "Base-Name" to baseName,
                    "Main-Class" to "com.abitofhelp.howdy.MainKt",
                    "Start-Class" to "",
                    "Implementation-Title" to "I am a title.",
                    "Implementation-Version" to version
                )
            )
        }
    }
}