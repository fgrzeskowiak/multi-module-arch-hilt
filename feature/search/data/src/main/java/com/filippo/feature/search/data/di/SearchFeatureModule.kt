package com.filippo.feature.search.data.di

import com.filippo.feature.search.data.SearchRepositoryImpl
import com.filippo.feature.search.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface SearchFeatureModule {
    @Binds
    @ViewModelScoped
    fun searchRepository(repository: SearchRepositoryImpl): SearchRepository
}
