package com.filippo.feature.login.data.di

import com.filippo.feature.login.data.remote.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LoginNetworkModule {

    @Provides
    @Singleton
    fun sessionApi(retrofit: Retrofit): LoginApi = retrofit.create()
}
