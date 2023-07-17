package com.filippo.core.session.data.di

import com.filippo.core.session.data.SessionProviderImpl
import com.filippo.core.session.domain.SessionProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SessionCoreModule {

    @Binds
    @Singleton
    fun sessionProvider(provider: SessionProviderImpl): SessionProvider
}
