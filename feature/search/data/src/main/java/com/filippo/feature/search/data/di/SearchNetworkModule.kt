package com.filippo.feature.search.data.di

import com.filippo.feature.search.data.remote.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object SearchNetworkModule {
    @Provides
    @Singleton
    fun searchApi(retrofit: Retrofit): SearchApi = retrofit.create()
}
