package com.app.juawcevada.vostextu.data

import arrow.core.Try
import com.app.juawcevada.vostextu.model.UserEntity
import dagger.Reusable
import ru.gildor.coroutines.retrofit.await
import javax.inject.Inject

@Reusable
class UsersRepository @Inject constructor(
    private val userService: ApiService
) {

    suspend fun getUsers(): Try<List<UserEntity>> = Try {
        userService.getUsers().await()
    }
}