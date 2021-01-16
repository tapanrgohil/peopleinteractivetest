package com.shaadi.test.model


import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)