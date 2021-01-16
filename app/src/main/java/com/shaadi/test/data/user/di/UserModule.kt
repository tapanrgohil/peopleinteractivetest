package com.shaadi.test.data.user.di

import com.shaadi.test.data.user.remote.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

//Dagger module for user repository
@Module(includes = [UserRepositoryModule::class])
@InstallIn(ApplicationComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }


}