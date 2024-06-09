import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    com.github.johnrengelman.shadow
}

tasks {
    shadowJar {
        archiveFileName = "packetevents-${project.name}-${project.version}.jar"
        archiveClassifier = null

        relocate("net.kyori.adventure.text.serializer.gson", "io.github.retrooper.packetevents.adventure.serializer.gson")
        relocate("net.kyori.adventure.text.serializer.json", "io.github.retrooper.packetevents.adventure.serializer.json")
        relocate("net.kyori.adventure.text.serializer.legacy", "io.github.retrooper.packetevents.adventure.serializer.legacy")
        relocate("net.kyori.option", "io.github.retrooper.packetevents.adventure.option")
        dependencies {
            exclude(dependency("com.google.code.gson:gson:.*"))
        }

        mergeServiceFiles()
    }

    assemble {
        dependsOn(shadowJar)
    }
}

configurations.implementation.get().extendsFrom(configurations.shadow.get())

gradle.taskGraph.whenReady {
    if (gradle.startParameter.taskNames.any { it.startsWith("publish") }) {
        logger.info("Adding shadow configuration to shadowJar tasks in module ${project.name}.")
        tasks.withType<ShadowJar> {
            dependencies {
                exclude { it.configuration == "shadow" }
            }
        }
    }
}