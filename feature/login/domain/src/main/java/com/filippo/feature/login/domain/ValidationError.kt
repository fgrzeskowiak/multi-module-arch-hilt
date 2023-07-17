package com.filippo.feature.login.domain

sealed interface ValidationError {
    val message: String?

    object Username : ValidationError {
        override val message: String = "Invalid username"
    }

    object Password : ValidationError {
        override val message: String = "Invalid password"
    }

    data class Request(override val message: String?) : ValidationError
}
