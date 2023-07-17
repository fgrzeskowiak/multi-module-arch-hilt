package com.filippo.feature.account.data.di

import com.filippo.domain.AccountRepository
import com.filippo.feature.account.data.AccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface AccountFeatureModule {
    @Binds
    @ActivityRetainedScoped
    fun accountRepository(repository: AccountRepositoryImpl): AccountRepository
}
