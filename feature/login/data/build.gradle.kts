plugins {
    id("android-data-module-script")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(project(":feature:login:domain"))
    implementation(project(":core:network:data"))
    implementation(project(":core:network:domain"))
    implementation(project(":core:session:domain"))

    implementation(libs.coroutines)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlinx.serialization)
    implementation(libs.arrow)

    implementation(libs.retrofit)
}
