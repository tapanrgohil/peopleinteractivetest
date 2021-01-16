package com.shaadi.test.data.user.remote

import com.google.gson.*
import com.shaadi.test.data.user.local.Gender
import java.lang.reflect.Type


class GenderSerializer : JsonDeserializer<Gender> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Gender {
        return when (json!!.asString) {
            Gender.MALE.name -> Gender.MALE
            Gender.FEMALE.name -> Gender.FEMALE
            else -> Gender.UNKNOWN
        }
    }


}