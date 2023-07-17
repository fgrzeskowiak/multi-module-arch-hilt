plugins {
    id("android-domain-module-script")
}

dependencies {
    implementation(libs.androidx.paging)

    implementation(libs.coroutines)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
