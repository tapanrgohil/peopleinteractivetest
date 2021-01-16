package com.shaadi.test.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.shaadi.test.data.core.Resource
import com.shaadi.test.data.user.UserRepository
import com.shaadi.test.ui.user.model.User

class UserCardViewModel @ViewModelInject constructor(private val userRepository: UserRepository) :
    ViewModel() {


    val userListLiveData = MediatorLiveData<Resource<List<User>>>()

    val lastSource: LiveData<Resource<List<User>>>? = null

    private val updateUserLiveData by lazy { MutableLiveData<User>() }

    val userUpdateResult = updateUserLiveData.switchMap {
        userRepository.updateUser(it)
            .asLiveData(viewModelScope.coroutineContext)
    }

    fun updateUserRequest(updateUser: User) {
        updateUserLiveData.value = updateUser
    }

    fun getUsersList() {
        lastSource?.let {
            userListLiveData.removeSource(it)
        }
        val lastSource = userRepository.getUsers(null, false)
            .asLiveData(viewModelScope.coroutineContext)
        userListLiveData.addSource(
            lastSource
        ) {
            userListLiveData.value = it
        }


    }

}