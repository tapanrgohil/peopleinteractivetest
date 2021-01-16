package com.shaadi.test.data.user.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


//Database related local implementation
class UserLocalSourceImpl @Inject constructor(private val userDao: UserDao) : UserLocalSource {
    override fun insertUsers(users: List<UserEntity>) {
        return userDao.upsertUsers(users)
    }

    override fun getUsers(query: String?): Flow<List<UserEntity>> {
        return if (query == null) userDao.getUsers() else TODO("Some thing to search with")
    }

    override fun updateUser(toEntity: UserEntity) {
        return userDao.updateUser(toEntity)
    }
}