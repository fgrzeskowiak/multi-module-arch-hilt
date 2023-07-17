plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

// uncomment if you want to use class Plugins
//gradlePlugin {
//    plugins {
//        val pluginsPackage = "com.filippo.gradle.plugins"
//        val androidModulePluginName = "android-module"
//        val androidDiModulePluginName = "android-di-module"
//        val androidPresentationModulePluginName = "android-presentation-module"
//        val androidAppPluginName = "android-app"
//
//        register(androidModulePluginName) {
//            id = androidModulePluginName
//            implementationClass = "$pluginsPackage.AndroidModulePlugin"
//        }
//        register(androidDiModulePluginName) {
//            id = androidDiModulePluginName
//            implementationClass = "$pluginsPackage.AndroidDiModulePlugin"
//        }
//        register(androidPresentationModulePluginName) {
//            id = androidPresentationModulePluginName
//            implementationClass = "$pluginsPackage.AndroidPresentationModulePlugin"
//        }
//        register(androidAppPluginName) {
//            id = androidAppPluginName
//            implementationClass = "$pluginsPackage.AndroidAppPlugin"
//        }
//    }
//}

dependencies {
    val kotlinVersion = "1.7.20"
    implementation(
        group = "com.android.tools.build",
        name = "gradle",
        version = "7.4.0"
    )
    implementation(kotlin("gradle-plugin", version = kotlinVersion))
    implementation(kotlin("serialization", version = kotlinVersion))
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.44")
}
