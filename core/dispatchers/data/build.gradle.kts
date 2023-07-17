plugins {
    id("android-data-module-script")
}

dependencies {
    implementation(project(":core:dispatchers:domain"))

    implementation(libs.bundles.coroutines)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
