package com.shaadi.test.data.user.remote

import com.shaadi.test.model.UsersResponse
import retrofit2.Response
import javax.inject.Inject

class UserRemoteSourceImp @Inject constructor(private val apiService: UserApiService) :
    UserRemoteSource {
    override suspend fun getUsers(result: Int): Response<UsersResponse> {
        return apiService.getUsers(result)
    }
}