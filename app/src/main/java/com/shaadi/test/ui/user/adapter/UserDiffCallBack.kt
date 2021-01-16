package com.shaadi.test.ui.user.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shaadi.test.ui.user.model.User

class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return newItem.requestStatus == oldItem.requestStatus
    }

}
