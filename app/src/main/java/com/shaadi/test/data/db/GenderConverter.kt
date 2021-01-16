package com.shaadi.test.data.db

import androidx.room.TypeConverter
import com.shaadi.test.data.user.local.Gender


class GenderConverter {

    @TypeConverter
    fun fromGender(gender: Gender): String {
        return gender.name
    }

    @TypeConverter
    fun toGender(gender: String): Gender {
        return when (gender) {
            Gender.MALE.name -> Gender.MALE
            Gender.FEMALE.name -> Gender.FEMALE
            else -> Gender.UNKNOWN
        }
    }

}