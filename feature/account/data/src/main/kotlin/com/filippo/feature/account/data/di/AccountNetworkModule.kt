package com.filippo.feature.account.data.di

import com.filippo.feature.account.data.remote.AccountApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object AccountNetworkModule {
    @Provides
    @Singleton
    fun accountApi(retrofit: Retrofit): AccountApi = retrofit.create()
}
