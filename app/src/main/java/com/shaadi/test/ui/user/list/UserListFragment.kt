package com.shaadi.test.ui.user.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.test.R
import com.shaadi.test.data.core.Status
import com.shaadi.test.exception.SnackbarManager.handleErrorResponse
import com.shaadi.test.ui.user.UserListViewModel
import com.shaadi.test.ui.user.adapter.UserListAdapter
import com.shaadi.test.ui.user.model.User
import com.shaadi.test.util.gone
import com.shaadi.test.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.fragment_user_list.pbLoading

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {


    private val userListViewModel by activityViewModels<UserListViewModel>()

    private val userAdapter = UserListAdapter() {
        userListViewModel.updateUserRequest(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        attachObserver()
        userListViewModel.getUsersList()
        savedInstanceState?.let { restoreState(it) }
    }

    private fun restoreState(savedInstanceState: Bundle) {
        (rvUsers.layoutManager as? LinearLayoutManager)?.onRestoreInstanceState(savedInstanceState)

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
                    srUsersList.isRefreshing = false
                }
                Status.ERROR -> {
                    pbLoading.gone()
                    srUsersList.isRefreshing = false
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
                    srUsersList.isRefreshing = false
                }
                Status.ERROR -> {
                    pbLoading.gone()
                    srUsersList.isRefreshing = false
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
        srUsersList?.setOnRefreshListener {
            userListViewModel.getUsersList()
        }
    }

    override fun onPause() {
        srUsersList.isRefreshing = false
        super.onPause()
    }

    private fun setupView() {
        rvUsers.layoutManager = LinearLayoutManager(context)
        rvUsers.adapter = userAdapter

    }

    override fun onSaveInstanceState(outState: Bundle) {
        (rvUsers.layoutManager as? LinearLayoutManager)?.onSaveInstanceState()
        super.onSaveInstanceState(outState)
    }


    override fun onDestroyView() {
        rvUsers?.adapter = null
        super.onDestroyView()
    }
}