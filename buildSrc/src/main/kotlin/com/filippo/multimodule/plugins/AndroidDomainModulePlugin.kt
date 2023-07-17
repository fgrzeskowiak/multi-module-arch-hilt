package com.filippo.multimodule.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidDomainModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("com.android.library")
            plugins.apply("kotlin-android")
            plugins.apply("jacoco")

            extensions.configure<LibraryExtension> {
                setupJvm()
                setupKotlin()
                setupDefaultConfig()
                setupProguard()
                setupFlavors()
                ignoreLibProdDebugVariant()
            }
        }
    }

    private fun LibraryExtension.setupProguard() {
        defaultConfig {
            consumerProguardFile("proguard-rules.pro")
        }
    }

    private fun LibraryExtension.setupFlavors() {
        val apiDimension = "api"
        flavorDimensions.add(apiDimension)
        productFlavors {
            create("staging") {
                dimension = apiDimension
            }
            create("prod") {
                dimension = apiDimension
            }
        }
    }
}
