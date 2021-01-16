package com.shaadi.test.data.user.local

import kotlinx.coroutines.flow.Flow

//Database related source
interface UserLocalSource {

    fun insertUsers(users: List<UserEntity>)
    fun getUsers(query: String? = null): Flow<List<UserEntity>>
    fun updateUser(toEntity: UserEntity)

}