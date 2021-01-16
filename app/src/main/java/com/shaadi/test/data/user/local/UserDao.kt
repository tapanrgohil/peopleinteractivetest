package com.shaadi.test.data.user.local

import androidx.room.*
import com.shaadi.test.data.db.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM USERS")
    abstract fun getUsers(): Flow<List<UserEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateUser(userEntity: UserEntity)


    //Try to insert-> there will be 2 case if item is already
    // there with same id it will update else insert new one
    //Currently we are not updating but still methods are ther from my base code
    @Transaction
    open fun upsertUsers(users: List<UserEntity>) {
        val insertResult = insertAll(users)
        val updateList = ArrayList<UserEntity>()

        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) {
                updateList.add(users[i])
            }
        }

        if (updateList.isNotEmpty()) {
            updateAll(updateList)
        }
    }
}