package com.shaadi.test.data.db

import androidx.room.TypeConverter
import com.shaadi.test.data.user.local.Gender
import com.shaadi.test.data.user.local.RequestStatus

class StatusConverter {

    @TypeConverter
    fun fromStatus(status: RequestStatus): Int {
        return status.index
    }

    @TypeConverter
    fun toGender(requsetStatus: Int): RequestStatus {
        return when (requsetStatus) {
            RequestStatus.PENDING.index -> RequestStatus.PENDING
            RequestStatus.ACCEPTED.index -> RequestStatus.ACCEPTED
            RequestStatus.REJECTED.index -> RequestStatus.REJECTED
            else -> RequestStatus.PENDING
        }
    }

}