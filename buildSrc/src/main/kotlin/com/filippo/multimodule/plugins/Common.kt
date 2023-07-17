package com.filippo.multimodule.plugins

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun BaseExtension.setupJvm() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

fun BaseExtension.setupKotlin() {
    // analogous to kotlinOptions {} block
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions> {
        setupKotlin()
    }
}

fun KotlinJvmOptions.setupKotlin() {
    jvmTarget = "11"
    freeCompilerArgs = freeCompilerArgs + listOf(
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
    )
}

fun TaskContainer.setupKotlin() {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )
        }
    }
}

fun BaseExtension.setupDefaultConfig() {
    defaultConfig {
        compileSdkVersion(TARGET_SDK)
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
    }
}

fun BaseExtension.setupBuildFeatures() {
    buildFeatures.viewBinding = true
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.3.2"
}

fun Project.ignoreLibProdDebugVariant() {
    extensions.configure<LibraryAndroidComponentsExtension> {
        beforeVariants {
            if (it.buildType == "debug" && it.flavorName == "prod") {
                it.enable = false
            }
        }
    }
}

fun Project.ignoreAppProdDebugVariant() {
    extensions.configure<ApplicationAndroidComponentsExtension> {
        beforeVariants {
            if (it.buildType == "debug" && it.flavorName == "prod") {
                it.enable = false
            }
        }
    }
}

private const val TARGET_SDK = 33
private const val MIN_SDK = 23
