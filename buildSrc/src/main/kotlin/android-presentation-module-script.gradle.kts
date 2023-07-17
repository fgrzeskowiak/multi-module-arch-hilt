import com.filippo.multimodule.plugins.setupBuildFeatures

plugins {
    id("android-data-module-script")
}

android {
    setupBuildFeatures()
}
