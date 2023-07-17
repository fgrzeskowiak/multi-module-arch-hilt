package com.filippo.feature.login.data

import arrow.core.Either
import arrow.core.raise.either
import com.filippo.core.network.domain.RequestError
import com.filippo.core.network.domain.apiCall
import com.filippo.core.session.domain.SessionProvider
import com.filippo.core.session.domain.models.SessionId
import com.filippo.feature.login.data.remote.LoginApi
import com.filippo.feature.login.data.remote.model.SessionRequest
import com.filippo.feature.login.data.remote.model.ValidateTokenRequest
import com.filippo.feature.login.domain.LoginRepository
import com.filippo.feature.login.domain.Password
import com.filippo.feature.login.domain.Username
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val sessionProvider: SessionProvider,
) : LoginRepository {

    override suspend fun login(username: Username, password: Password): Either<RequestError, Unit> =
        either {
            val createdToken = apiCall { loginApi.createToken() }.bind()
            val validatedToken = apiCall {
                loginApi.validateToken(
                    ValidateTokenRequest(
                        createdToken.requestToken,
                        username.value,
                        password.value
                    )
                )
            }.bind()

            val session = apiCall {
                loginApi.createSession(SessionRequest(validatedToken.requestToken))
            }
            val sessionId = session.fold({ "" }, { it.sessionId.value })
            sessionProvider.saveSessionId(SessionId(sessionId))
        }
}
