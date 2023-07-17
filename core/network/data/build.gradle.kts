import com.filippo.multimodule.extensions.createSecret

plugins {
    id("android-data-module-script")
}

android {
    defaultConfig {
        createSecret("MOVIES_ACCESS_TOKEN")
    }

    productFlavors {
        getByName("staging") {
            createSecret(key = "MOVIES_STAGING_BASE_URL", name = "MOVIES_BASE_URL")
        }
        getByName("prod") {
            createSecret(key = "MOVIES_PROD_BASE_URL", name = "MOVIES_BASE_URL")
        }
    }
}

dependencies {
    implementation(project(":core:network:domain"))
    implementation(libs.bundles.coroutines)
    api(libs.bundles.network)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
