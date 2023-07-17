import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("android-presentation-module-script")
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
