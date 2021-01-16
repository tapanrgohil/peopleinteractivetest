package com.shaadi.test.data.user.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "USERS")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    var id: String,
    @ColumnInfo(name = "TITLE")
    var title: String,
    @ColumnInfo(name = "FIRST")
    var first: String,
    @ColumnInfo(name = "LAST")
    var last: String,
    @ColumnInfo(name = "EMAIL")
    var email: String,
    @ColumnInfo(name = "PHONE")
    var phone: String,
    @ColumnInfo(name = "DOB")
    var date: Date,
    @ColumnInfo(name = "THUMBNAIL")
    var thumbnail: String,
    @ColumnInfo(name = "PICTURE")
    var picture: String,
    @ColumnInfo(name = "Gender")
    var gender: Gender
) {
    var requestStatus: RequestStatus = RequestStatus.PENDING
}


sealed class Gender(val name: String) {
    object MALE : Gender("male")
    object FEMALE : Gender("female")
    object UNKNOWN : Gender("unknown")
}

sealed class RequestStatus(val index: Int) {
    object PENDING : RequestStatus(1)
    object REJECTED : RequestStatus(2)
    object ACCEPTED : RequestStatus(3)
}