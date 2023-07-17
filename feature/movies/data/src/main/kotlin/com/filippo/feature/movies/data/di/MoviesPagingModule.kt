package com.filippo.feature.movies.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.filippo.feature.movies.data.MoviesPagingDataSource
import com.filippo.feature.movies.domain.models.Movie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MoviesPagingModule {
    @Provides
    @Singleton
    fun providePager(dataSource: MoviesPagingDataSource): Pager<Int, Movie> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dataSource }
        )

    private const val PAGE_SIZE = 20
}
