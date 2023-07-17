@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.filippo.multimodule.plugins.ignoreAppProdDebugVariant
import com.filippo.multimodule.plugins.setupBuildFeatures
import com.filippo.multimodule.plugins.setupDefaultConfig
import com.filippo.multimodule.plugins.setupJvm
import com.filippo.multimodule.plugins.setupKotlin

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("jacoco")
}

android {
    setupJvm()
    kotlinOptions {
        setupKotlin()
    }
    setupDefaultConfig()
    setupBuildFeatures()
    setupLint()
    setupBuildTypes()
    setupFlavors()
    ignoreAppProdDebugVariant()
}

fun BaseAppModuleExtension.setupBuildTypes() {
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

fun BaseAppModuleExtension.setupLint() {
    lint {
        abortOnError = false
        xmlReport = true
        checkDependencies = true
    }
}

fun BaseAppModuleExtension.setupFlavors() {
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
