package com.filippo.feature.login.data.di

import com.filippo.feature.login.data.LoginRepositoryImpl
import com.filippo.feature.login.domain.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LoginFeatureModule {
    @Binds
    @Singleton
    fun loginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository
}
