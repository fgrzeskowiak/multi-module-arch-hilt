package com.filippo.core.dispatchers.data.di

import com.filippo.core.dispatchers.domain.DispatchersFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class DispatchersFactoryImpl @Inject constructor(): DispatchersFactory {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun default(): CoroutineDispatcher = Dispatchers.Default
    override fun main(): CoroutineDispatcher = Dispatchers.Main.immediate
}
