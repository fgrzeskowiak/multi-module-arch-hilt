package com.filippo.feature.login.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ValidateTokenRequest(
    @SerialName("request_token") val requestToken: RequestToken,
    val username: String,
    val password: String
)
