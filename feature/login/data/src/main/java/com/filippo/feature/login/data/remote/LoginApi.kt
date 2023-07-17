package com.filippo.feature.login.data.remote

import com.filippo.feature.login.data.remote.model.SessionRequest
import com.filippo.feature.login.data.remote.model.SessionResponse
import com.filippo.feature.login.data.remote.model.ValidateTokenRequest
import com.filippo.feature.login.data.remote.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface LoginApi {
    @GET("3/authentication/token/new")
    suspend fun createToken(): TokenResponse

    @POST("3/authentication/token/validate_with_login")
    suspend fun validateToken(@Body request: ValidateTokenRequest): TokenResponse

    @POST("3/authentication/session/new")
    suspend fun createSession(@Body request: SessionRequest): SessionResponse
}
