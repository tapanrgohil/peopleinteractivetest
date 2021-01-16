package com.shaadi.test.ui.user.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import com.shaadi.test.R
import com.shaadi.test.data.user.local.RequestStatus
import com.shaadi.test.ui.user.model.User
import com.shaadi.test.util.gone
import com.shaadi.test.util.toReadableDate
import com.shaadi.test.util.visible
import kotlinx.android.synthetic.main.item_user.view.ivUserImage
import kotlinx.android.synthetic.main.item_user.view.tvAddress
import kotlinx.android.synthetic.main.item_user.view.tvDob
import kotlinx.android.synthetic.main.item_user.view.tvGender
import kotlinx.android.synthetic.main.item_user.view.tvName
import kotlinx.android.synthetic.main.item_user.view.tvStatus
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserListAdapter(
    private val onButtonClicked: (User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val differ: AsyncListDiffer<User> =
        object : AsyncListDiffer<User>(this, UserDiffCallBack()) {}


    fun setData(list: List<User>) {
        differ.submitList(list)

    }


    class UserViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun updateData(user: User, onButtonClicked: (User) -> Unit) {
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
                        btAccept.visible()
                        btReject.visible()
                    }
                    is RequestStatus.ACCEPTED -> {
                        tvStatus.text = "You already have Accepted request"
                        tvStatus.setTextColor(Color.GREEN)
                        tvStatus.visible()
                        btAccept.gone()
                        btReject.gone()
                    }
                    is RequestStatus.REJECTED -> {
                        tvStatus.text = "You already have Rejected request"
                        tvStatus.setTextColor(Color.RED)
                        btAccept.gone()
                        tvStatus.visible()
                        btReject.gone()
                    }
                }
                btAccept.setOnClickListener {
                    user.requestStatus = RequestStatus.ACCEPTED
                    onButtonClicked.invoke(user)

                }
                btReject.setOnClickListener {
                    user.requestStatus = RequestStatus.REJECTED
                    onButtonClicked.invoke(user)

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = with(LayoutInflater.from(parent.context)) {
            inflate(R.layout.item_user_list, parent, false)
        }
        return UserViewHolder(view)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.updateData(differ.currentList[position], onButtonClicked)
    }


}



