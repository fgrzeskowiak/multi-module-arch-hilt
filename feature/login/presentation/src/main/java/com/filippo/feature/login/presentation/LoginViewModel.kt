package com.filippo.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippo.feature.login.domain.LoginState
import com.filippo.feature.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val loginFlow = MutableSharedFlow<Credentials>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginFlow.emit(username to password)
        }
    }

    val loginState: Flow<LoginState> = loginFlow
        .flatMapLatest { (username, password) ->
            flow { emit(loginUseCase.login(username, password)) }
                .onStart { emit(LoginState.Loading) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LoginState.Initial)
}

private typealias Credentials = Pair<String, String>
