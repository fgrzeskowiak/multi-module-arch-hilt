package com.filippo.feature.login.domain

import arrow.core.Either
import com.filippo.core.network.domain.RequestError

interface LoginRepository {
    suspend fun login(username: Username, password: Password): Either<RequestError, Unit>
}
