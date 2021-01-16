package com.shaadi.test.data.user

import com.shaadi.test.data.core.Resource
import com.shaadi.test.ui.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    //if we want to force update data from server and perform any search operation
    fun getUsers(query: String? = null, forceRefresh: Boolean = false): Flow<Resource<List<User>>>
    fun updateUser(user: User): Flow<Resource<Any>>
}