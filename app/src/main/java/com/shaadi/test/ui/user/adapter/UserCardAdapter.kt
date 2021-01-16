package com.shaadi.test.ui.user.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.Transformation
import com.shaadi.test.R
import com.shaadi.test.data.user.local.RequestStatus
import com.shaadi.test.ui.user.model.User
import com.shaadi.test.util.gone
import com.shaadi.test.util.toReadableDate
import com.shaadi.test.util.visible
import kotlinx.android.synthetic.main.item_user.view.*

class UserCardAdapter() : RecyclerView.Adapter<UserCardAdapter.UserViewHolder>() {

    private var firstCommit: Boolean = false
    private val differ: AsyncListDiffer<User> =
        object : AsyncListDiffer<User>(this, UserDiffCallBack()) {}


    fun setData(list: List<User>) {

        if (!firstCommit) {
            differ.submitList(list) {
                notifyDataSetChanged()
            }
            firstCommit = true
        } else {
            differ.submitList(list)
        }

    }


    class UserViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun updateData(user: User) {
            itemView.apply {
                tvName.text = user.title.plus(" ").plus(user.first).plus(" ").plus(user.last)
                tvAddress.text = user.first
                tvGender.text = user.gender.name
                tvDob.text = user.date.toReadableDate()
                ivUserImage?.load(user.picture) {
                    diskCachePolicy(CachePolicy.ENABLED)
                }
                when (user.requestStatus) {
                    is RequestStatus.PENDING -> {
                        tvStatus.text = ""
                        tvStatus.gone()
                    }
                    is RequestStatus.ACCEPTED -> {
                        tvStatus.text = "You already have Accepted request"
                        tvStatus.setTextColor(Color.GREEN)
                        tvStatus.visible()
                    }
                    is RequestStatus.REJECTED -> {
                        tvStatus.text = "You already have Rejected request"
                        tvStatus.setTextColor(Color.RED)
                        tvStatus.visible()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = with(LayoutInflater.from(parent.context)) {
            inflate(R.layout.item_user, parent, false)
        }
        return UserViewHolder(view)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.updateData(differ.currentList[position])
    }


    fun getItemAt(position: Int): User? {
        return if (position < differ.currentList.size) {
            differ.currentList[position]
        } else {
            null
        }
    }

}


