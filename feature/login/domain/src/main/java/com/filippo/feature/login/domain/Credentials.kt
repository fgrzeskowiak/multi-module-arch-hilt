package com.filippo.feature.login.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

@JvmInline
value class Username private constructor(val value: String) {
    companion object {
        fun from(username: String): Either<ValidationError, Username> = either {
            ensure(username.isNotBlank()) { ValidationError.Username }
            Username(username)
        }
    }
}

@JvmInline
value class Password private constructor(val value: String) {
    companion object {
        fun from(password: String): Either<ValidationError, Password> = either {
            ensure(password.isNotBlank()) { ValidationError.Password }
            Password(password)
        }
    }
}
