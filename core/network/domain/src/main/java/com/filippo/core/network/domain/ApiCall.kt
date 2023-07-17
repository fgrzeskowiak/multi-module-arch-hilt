package com.filippo.core.network.domain

import arrow.core.Either
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException

suspend inline fun <Response> apiCall(
    crossinline request: suspend () -> Response
): Either<RequestError, Response> =
    Either.catch { request() }
        .mapLeft { it.toRequestError() }

fun Throwable.toRequestError() = when (this) {
    is UnknownHostException -> RequestError.NoConnection
    is SocketTimeoutException -> RequestError.Timeout
    !is HttpException -> RequestError.Unknown
    else -> RequestError.Unknown
}

sealed interface RequestError {
    object NoConnection : RequestError
    object Timeout : RequestError
    object Unknown : RequestError
}
