package com.filippo.feature.login.data.remote.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class RequestToken(val token: String)
