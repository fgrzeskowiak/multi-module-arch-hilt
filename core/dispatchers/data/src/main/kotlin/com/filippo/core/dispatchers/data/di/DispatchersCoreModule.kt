package com.filippo.core.dispatchers.data.di

import com.filippo.core.dispatchers.domain.DispatchersFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DispatchersCoreModule {
    @Binds
    @Singleton
    fun dispatchersFactory(factoryImpl: DispatchersFactoryImpl): DispatchersFactory
}
