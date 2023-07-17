package com.filippo.multimodule.plugins

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

@Suppress("UnstableApiUsage")
class AndroidAppPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("com.android.application")
            plugins.apply("kotlin-android")
            plugins.apply("kotlin-kapt")
            plugins.apply("dagger.hilt.android.plugin")
            plugins.apply("jacoco")

            // configure<AppExtensions> { <- will be removed in Gradle 8
            extensions.configure<BaseAppModuleExtension> {
                setupJvm()
                setupDefaultConfig()
                setupBuildFeatures()
                setupLint()
                setupKotlin()
                setupBuildTypes()
                setupFlavors()
            }
        }
    }

    private fun BaseAppModuleExtension.setupBuildTypes() {
        buildTypes {
            getByName("debug") {
                versionNameSuffix = "-DEBUG"
                applicationIdSuffix = ".debug"
                enableUnitTestCoverage = false
                isDebuggable = true
                isMinifyEnabled = false
                isShrinkResources = false
            }
            getByName("release") {
                enableUnitTestCoverage = true
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    private fun BaseAppModuleExtension.setupLint() {
        lint {
            abortOnError = false
            xmlReport = true
            checkDependencies = true
        }
    }

    private fun BaseAppModuleExtension.setupFlavors() {
        flavorDimensions.add("api")
        productFlavors {
            create("staging") {
                dimension = "api"
                versionNameSuffix = "-STAGING"
                applicationIdSuffix = ".staging"
            }
            create("prod") {
                dimension = "api"
            }
        }
    }
}
