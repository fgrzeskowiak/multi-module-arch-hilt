plugins {
    id("kotlin-module-script")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:network:domain"))

    implementation(libs.dagger)
    implementation(libs.arrow)
    kapt(libs.dagger.compiler)
}
