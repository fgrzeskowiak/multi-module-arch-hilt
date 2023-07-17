package com.filippo.core.session.data.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SessionId(val id: String)
