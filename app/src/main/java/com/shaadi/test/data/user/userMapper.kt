package com.shaadi.test.data.user

import com.shaadi.test.data.user.local.UserEntity
import com.shaadi.test.model.Result
import com.shaadi.test.ui.user.model.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserMapper @Inject constructor() {

    fun entityToUi(userEntity: UserEntity) = with(userEntity) {
        User(id, title, first, last, email, phone, date, thumbnail, picture, gender, requestStatus)
    }

    fun responseToEntity(usersResponse: Result) = with(usersResponse) {
        UserEntity(
            login.uuid,
            name.title,
            name.first,
            name.last,
            email,
            phone,
            dob.date,
            picture.thumbnail,
            picture.large,
            gender
        )
    }

    fun toEntity(user: User): UserEntity = with(user) {
        val entity = UserEntity(
            id, title, first, last, email, phone, date, thumbnail, picture, gender
        )
        entity.requestStatus = requestStatus
        return@with entity
    }
}