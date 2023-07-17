package com.filippo.feature.login.domain

import arrow.core.nonEmptyListOf
import arrow.core.raise.either
import arrow.core.raise.zipOrAccumulate
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    suspend fun login(username: String, password: String): LoginState =
        either {
            val (validatedUsername, validatedPassword) = zipOrAccumulate(
                { Username.from(username).bind() },
                { Password.from(password).bind() },
                ::Credentials
            )

            loginRepository.login(validatedUsername, validatedPassword)
                .mapLeft { nonEmptyListOf(ValidationError.Request(it.toString())) }
                .bind()

        }.fold(
            ifLeft = { LoginState.Error(it) },
            ifRight = { LoginState.Success }
        )
}

private typealias Credentials = Pair<Username, Password>
