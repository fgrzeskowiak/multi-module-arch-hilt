plugins {
    id("android-data-module-script")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core:session:domain"))
    implementation(project(":core:dispatchers:domain"))

    implementation(libs.androidx.security)

    implementation(libs.coroutines)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)

    implementation(libs.kotlinx.serialization)
}
