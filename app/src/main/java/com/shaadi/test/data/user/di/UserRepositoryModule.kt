package com.shaadi.test.data.user.di

import com.shaadi.test.data.user.UserRepository
import com.shaadi.test.data.user.UserRepositoryImpl
import com.shaadi.test.data.user.local.UserLocalSource
import com.shaadi.test.data.user.local.UserLocalSourceImpl
import com.shaadi.test.data.user.remote.UserRemoteSource
import com.shaadi.test.data.user.remote.UserRemoteSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

@Module
@InstallIn(ApplicationComponent::class)
abstract class UserRepositoryModule {


    @Binds
    @Singleton
    abstract fun provideUserLocal(impl: UserLocalSourceImpl): UserLocalSource

    @Binds
    @Singleton
    abstract fun provideUserRemoteSource(impl: UserRemoteSourceImp): UserRemoteSource

    @ExperimentalContracts
    @Binds
    @Singleton
    abstract fun provideUserRepository(impl: UserRepositoryImpl): UserRepository
}