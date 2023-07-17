plugins {
    id("android-data-module-script")
}

dependencies {
    implementation(project(":core:images:domain"))
    implementation(project(":core:dispatchers:domain"))

    implementation(libs.coil)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.okhttp)
}
