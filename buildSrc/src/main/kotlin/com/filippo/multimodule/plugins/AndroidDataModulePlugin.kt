package com.filippo.multimodule.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidDataModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("android-domain-module")
            plugins.apply("kotlin-kapt")
            plugins.apply("dagger.hilt.android.plugin")
        }
    }
}
