plugins {
    id("android-data-module-script")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(project(":feature:search:domain"))

    implementation(project(":core:dispatchers:domain"))
    implementation(project(":core:images:domain"))
    implementation(project(":core:network:domain"))

    implementation(project(":feature:account:domain"))

    implementation(libs.androidx.paging)

    implementation(libs.coil)

    implementation(libs.kotlinx.serialization)
    implementation(libs.coroutines)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
}
