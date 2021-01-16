package com.shaadi.test.data.user.remote

import com.shaadi.test.model.UsersResponse
import retrofit2.Response
import retrofit2.http.Query

interface UserRemoteSource {

    suspend fun getUsers(@Query("results") result: Int): Response<UsersResponse>

}