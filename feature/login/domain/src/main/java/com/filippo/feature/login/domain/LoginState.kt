package com.filippo.feature.login.domain

sealed interface LoginState {
    object Initial : LoginState
    object Loading : LoginState
    data class Error(val errors: List<ValidationError>) : LoginState
    object Success : LoginState
}
