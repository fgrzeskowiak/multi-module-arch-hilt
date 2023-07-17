package com.filippo.multimodule.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

    class AndroidPresentationModulePlugin : Plugin<Project> {
        override fun apply(project: Project) {
            with(project) {
                plugins.apply("android-module")
                plugins.apply("kotlin-kapt")
                plugins.apply("dagger.hilt.android.plugin")

                extensions.configure<LibraryExtension> {
                    setupBuildFeatures()
                }
            }
        }
    }
