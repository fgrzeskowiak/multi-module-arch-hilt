plugins {
    id("android-app-script")
}

dependencies {
    implementation(project(":core:dispatchers:data"))
    implementation(project(":core:images:data"))
    implementation(project(":core:network:domain"))
    implementation(project(":core:session:data"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:account:data"))
    implementation(project(":feature:login:data"))
    implementation(project(":feature:movies:data"))
    implementation(project(":feature:search:data"))

    implementation(project(":feature:account:presentation"))
    implementation(project(":feature:login:presentation"))
    implementation(project(":feature:movies:presentation"))
    implementation(project(":feature:search:presentation"))

    implementation(libs.androidx.material)

    implementation(libs.bundles.androidx.navigation)

    implementation(libs.bundles.compose)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
