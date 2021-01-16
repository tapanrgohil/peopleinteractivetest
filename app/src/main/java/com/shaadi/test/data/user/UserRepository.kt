package com.shaadi.test.data.user

import com.shaadi.test.data.core.Resource
import com.shaadi.test.ui.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    //if we want to force update data from server and perform any search operation
    //Get users from dabase / if data is blank user it will fetch from api
    fun getUsers(query: String? = null, forceRefresh: Boolean = false): Flow<Resource<List<User>>>

    //Update user request status
    fun updateUser(user: User): Flow<Resource<Any>>
}