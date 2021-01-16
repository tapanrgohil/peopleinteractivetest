package com.shaadi.test.data.user.remote

import com.shaadi.test.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit interface
interface UserApiService {
    @GET("api")
    suspend fun getUsers(@Query("results") result: Int): Response<UsersResponse>
}