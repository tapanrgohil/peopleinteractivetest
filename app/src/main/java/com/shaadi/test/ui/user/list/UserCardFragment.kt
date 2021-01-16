package com.shaadi.test.ui.user.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shaadi.test.R
import com.shaadi.test.data.core.Status
import com.shaadi.test.data.user.local.RequestStatus
import com.shaadi.test.exception.SnackbarManager.handleErrorResponse
import com.shaadi.test.ui.user.UserCardViewModel
import com.shaadi.test.ui.user.adapter.UserCardAdapter
import com.shaadi.test.ui.user.model.User
import com.shaadi.test.util.gone
import com.shaadi.test.util.visible
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_card.*

@AndroidEntryPoint
class UserCardFragment : Fragment(R.layout.fragment_user_card) {


    private val userListViewModel by viewModels<UserCardViewModel>()

    private val userAdapter = UserCardAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        attachObserver()
        userListViewModel.getUsersList()
    }

    private fun attachObserver() {
        userListViewModel.userListLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    pbLoading.visible()
                }
                Status.SUCCESS -> {
                    setData(it.data.orEmpty())
                    pbLoading.gone()
                }
                Status.ERROR -> {
                    pbLoading.gone()
                    handleErrorResponse(it.retrofitResponse, it.throwable)
                }
            }
        })
        userListViewModel.userUpdateResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    pbLoading.visible()
                }
                Status.SUCCESS -> {
                    pbLoading.gone()
                }
                Status.ERROR -> {
                    pbLoading.gone()
                    handleErrorResponse(it.retrofitResponse, it.throwable)
                }
            }
        })
    }

    private fun setData(userList: List<User>) {
        userAdapter.setData(userList)
    }

    private fun initUI() {
        setupView()
    }

    private fun setupView() {
        cardStackView.layoutManager =
            CardStackLayoutManager(cardStackView.context, object : CardStackListener {
                override fun onCardDragging(direction: Direction?, ratio: Float) {

                }

                override fun onCardSwiped(direction: Direction?) {

                }

                override fun onCardRewound() {
                }

                override fun onCardCanceled() {
                }

                override fun onCardAppeared(view: View?, position: Int) {
                    val userItem = userAdapter.getItemAt(position)
                    Log.d("TAG", "onCardAppeared: ${userItem?.first}")
                    if (userItem?.requestStatus == RequestStatus.PENDING) {
                        btAccept.visible()
                        btReject.visible()
                    } else {
                        btAccept.gone()
                        btReject.gone()
                    }

                }

                override fun onCardDisappeared(view: View?, position: Int) {
                    if (position == userAdapter.itemCount - 1) {
                        noMore.visible()
                        btAccept.gone()
                        btReject.gone()
                    }

                }
            }).apply {
                setDirections(Direction.FREEDOM)
                setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
                setSwipeThreshold(.3f)

            }

        cardStackView.adapter = userAdapter

        btReject.setOnClickListener {
            cardStackView.swipe()
            rejectUser()
        }
        btAccept.setOnClickListener {
            acceptUser()
        }
    }

    private fun acceptUser() {
        cardStackView.swipe()
        getCurrentItem()?.let {
            it.requestStatus = RequestStatus.ACCEPTED
            userListViewModel.updateUserRequest(it)
        }
    }

    private fun getCurrentItem() = userAdapter.getItemAt(
        (cardStackView.layoutManager as CardStackLayoutManager)
            .topPosition
    )

    private fun rejectUser() {
        cardStackView.swipe()
        getCurrentItem()?.let {
            it.requestStatus = RequestStatus.REJECTED
            userListViewModel.updateUserRequest(it)
        }
    }

}