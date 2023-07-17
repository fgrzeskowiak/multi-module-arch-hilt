package com.filippo.multimodule.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("kotlin")
        project.tasks.setupKotlin()
    }
}
