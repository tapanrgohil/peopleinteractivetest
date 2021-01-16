package com.shaadi.test.ui.user.model

import com.shaadi.test.data.user.local.Gender
import com.shaadi.test.data.user.local.RequestStatus
import java.util.*

data class User(
    var id: String,
    var title: String,
    var first: String,
    var last: String,
    var email: String,
    var phone: String,
    var date: Date,
    var thumbnail: String,
    var picture: String,
    var gender: Gender,
    var requestStatus: RequestStatus
)