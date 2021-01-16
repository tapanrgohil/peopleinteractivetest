package com.shaadi.test.model


import com.google.gson.annotations.SerializedName

data class Registered(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)