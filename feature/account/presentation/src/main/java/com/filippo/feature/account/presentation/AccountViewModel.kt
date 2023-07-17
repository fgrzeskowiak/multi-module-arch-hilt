package com.filippo.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippo.domain.AccountDetails
import com.filippo.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AccountViewModel @Inject constructor(
    accountDetailsRepository: AccountRepository
) : ViewModel() {

    private val retryFlow = MutableSharedFlow<Unit>()

    val state: Flow<AccountScreenState> = retryFlow
        .onStart { emit(Unit) }
        .map { accountDetailsRepository.isLoggedIn() }
        .flatMapLatest { isLoggedIn ->
            if (isLoggedIn) {
                flow { emit(accountDetailsRepository.accountDetails()) }
                    .map<AccountDetails, AccountScreenState> { AccountScreenState.Success(it) }
                    .catch { emit(AccountScreenState.Failure(it)) }
                    .onStart { emit(AccountScreenState.Loading) }
            } else {
                flowOf(AccountScreenState.NeedsLogin)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AccountScreenState.Loading)

    fun retry() {
        viewModelScope.launch {
            retryFlow.emit(Unit)
        }
    }
}

internal sealed class AccountScreenState {
    object NeedsLogin : AccountScreenState()
    data class Success(val accountDetails: AccountDetails) : AccountScreenState()
    data class Failure(val error: Throwable) : AccountScreenState()
    object Loading : AccountScreenState()
}
