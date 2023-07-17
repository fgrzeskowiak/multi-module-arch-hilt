package com.filippo.core.dispatchers.domain

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersFactory {
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
