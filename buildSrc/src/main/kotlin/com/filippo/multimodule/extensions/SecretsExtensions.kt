package com.filippo.multimodule.extensions

import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.variant.BuildConfigField
import com.android.build.api.variant.LibraryVariant
import com.filippo.multimodule.utils.SecretsUtil
import com.android.build.api.dsl.ProductFlavor

    fun LibraryDefaultConfig.createSecret(key: String) {
        buildConfigField(SECRET_TYPE, key, SecretsUtil.getSecret(key))
    }

fun LibraryDefaultConfig.createSecret(key: String, name: String) {
    buildConfigField(SECRET_TYPE, name, SecretsUtil.getSecret(key))
}

fun LibraryVariant.createSecret(key: String, name: String) {
    buildConfigFields.put(name, BuildConfigField(SECRET_TYPE, SecretsUtil.getSecret(key), null))
}

fun ProductFlavor.createSecret(key: String, name: String) {
    buildConfigField(SECRET_TYPE, name, SecretsUtil.getSecret(key))
}

private const val SECRET_TYPE = "String"
