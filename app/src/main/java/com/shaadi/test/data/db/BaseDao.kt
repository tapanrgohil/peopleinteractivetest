package com.shaadi.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

//Base class for dao for common daos
@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(data: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(data: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(data: List<T>)

}