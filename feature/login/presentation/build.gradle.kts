plugins {
    id("android-presentation-module-script")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:login:domain"))

    implementation(libs.bundles.androidx.ui)
    implementation(libs.bundles.androidx.navigation)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.coroutines)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
