package com.shaadi.test.di

import android.app.Application
import androidx.room.Room
import com.shaadi.test.data.db.UserDatabase
import com.shaadi.test.data.user.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    private const val DB_NAME = "shaadi-db"

    @Provides
    @Singleton
    fun provideDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application, UserDatabase::class.java, DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()

}