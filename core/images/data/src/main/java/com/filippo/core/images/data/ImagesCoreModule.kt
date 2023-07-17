package com.filippo.core.images.data

import com.filippo.core.images.domain.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ImagesCoreModule {
    @Binds
    @Singleton
    fun imagesRepository(repository: ImagesRepositoryImpl): ImagesRepository
}
