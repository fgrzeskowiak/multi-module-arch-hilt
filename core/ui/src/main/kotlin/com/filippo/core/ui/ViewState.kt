package com.filippo.core.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class ViewState<out SuccessType : Any> {
    data class Success<SuccessType : Any>(val data: SuccessType) : ViewState<SuccessType>()
    data class Failure(val error: Throwable) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()

    val isLoading: Boolean get() = this == Loading
    val isFailure: Boolean get() = this is Failure
    val isSuccess: Boolean get() = this is Success

    inline fun onSuccess(action: (SuccessType) -> Unit): ViewState<SuccessType> {
        if (this is Success) action(data)
        return this
    }

    inline fun onFailure(action: (Throwable) -> Unit): ViewState<SuccessType> {
        if (this is Failure) action(error)
        return this
    }

    inline fun onLoading(action: () -> Unit): ViewState<SuccessType> {
        if (this is Loading) action()
        return this
    }
}

fun <SuccessType : Any> Flow<SuccessType>.toViewState(): Flow<ViewState<SuccessType>> = this
    .map<SuccessType, ViewState<SuccessType>> { ViewState.Success(it) }
    .catch { emit(ViewState.Failure(it)) }
    .onStart { emit(ViewState.Loading) }

inline fun <SuccessType : Any, MappedType : Any> Flow<SuccessType>.toViewState(
    crossinline mapper: (SuccessType) -> MappedType
): Flow<ViewState<MappedType>> = this
    .map<SuccessType, ViewState<MappedType>> { ViewState.Success(mapper(it)) }
    .catch { emit(ViewState.Failure(it)) }
    .onStart { emit(ViewState.Loading) }
