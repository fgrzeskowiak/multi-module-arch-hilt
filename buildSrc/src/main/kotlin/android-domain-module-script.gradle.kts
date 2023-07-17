import com.android.build.gradle.LibraryExtension
import com.filippo.multimodule.plugins.ignoreLibProdDebugVariant
import com.filippo.multimodule.plugins.setupDefaultConfig
import com.filippo.multimodule.plugins.setupJvm
import com.filippo.multimodule.plugins.setupKotlin

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    setupJvm()
    kotlinOptions {
        setupKotlin()
    }
    setupDefaultConfig()
    setupProguard()
    setupFlavors()
    ignoreLibProdDebugVariant()
}


fun LibraryExtension.setupProguard() {
    defaultConfig {
        consumerProguardFile("proguard-rules.pro")
    }
}

fun LibraryExtension.setupFlavors() {
    val dimensionName = "api"
    flavorDimensions.add(dimensionName)
    productFlavors {
        create("staging") {
            dimension = dimensionName
        }
        create("prod") {
            dimension = dimensionName
        }
    }
}
