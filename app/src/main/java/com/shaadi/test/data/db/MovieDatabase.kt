package com.shaadi.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shaadi.test.data.db.DateTimeConverter
import com.shaadi.test.data.user.local.UserDao
import com.shaadi.test.data.user.local.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1
)
@TypeConverters(DateTimeConverter::class, GenderConverter::class, StatusConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}