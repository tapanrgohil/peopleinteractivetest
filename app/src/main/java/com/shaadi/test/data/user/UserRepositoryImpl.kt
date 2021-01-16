package com.shaadi.test.data.user

import com.shaadi.test.data.core.Resource
import com.shaadi.test.data.core.getFlow
import com.shaadi.test.data.user.local.UserLocalSource
import com.shaadi.test.data.user.remote.UserRemoteSource
import com.shaadi.test.ui.user.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts

class UserRepositoryImpl @Inject constructor(
    private val localSource: UserLocalSource,
    private val remoteSource: UserRemoteSource,
    private val mapper: UserMapper
) : UserRepository {
    @ExperimentalContracts
    override fun getUsers(
        query: String?,
        forceRefresh: Boolean
    ): Flow<Resource<List<User>>> {
        return getFlow(
            localFlow = { localSource.getUsers(query).map { it.map { mapper.entityToUi(it) } } },
            remote = { remoteSource.getUsers(10) },
            saveToDb = { localSource.insertUsers(it.results.map { mapper.responseToEntity(it) }) },
            forceRefresh = {
                it.isNullOrEmpty() || forceRefresh
            },
        )
    }

    override fun updateUser(user: User): Flow<Resource<Any>> {
        return getFlow(
            local = { localSource.updateUser(mapper.toEntity(user)) }
        )
    }
}