plugins {
    id("android-data-module-script")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(project(":feature:account:domain"))
    implementation(project(":core:session:domain"))

    implementation(libs.coroutines)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit)
}
