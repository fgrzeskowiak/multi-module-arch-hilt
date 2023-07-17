package com.filippo.feature.movies.data.di

import com.filippo.feature.movies.data.FavouritesRepositoryImpl
import com.filippo.feature.movies.data.MoviesRepositoryImpl
import com.filippo.feature.movies.domain.FavouritesRepository
import com.filippo.feature.movies.domain.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface MoviesFeatureModule {
    @Binds
    @ActivityRetainedScoped
    fun moviesRepository(repository: MoviesRepositoryImpl): MoviesRepository

    @Binds
    @ActivityRetainedScoped
    fun favouritesRepository(repository: FavouritesRepositoryImpl): FavouritesRepository
}
