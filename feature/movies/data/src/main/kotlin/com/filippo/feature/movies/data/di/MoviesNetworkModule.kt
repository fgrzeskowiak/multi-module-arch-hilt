package com.filippo.feature.movies.data.di

import com.filippo.feature.movies.data.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object MoviesNetworkModule {

    @Provides
    @Singleton
    fun moviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()
}
