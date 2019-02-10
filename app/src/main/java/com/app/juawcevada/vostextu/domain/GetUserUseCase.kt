package com.app.juawcevada.vostextu.domain

import arrow.core.Try
import com.app.juawcevada.vostextu.data.UsersRepository
import com.app.juawcevada.vostextu.domain.shared.UseCase
import com.app.juawcevada.vostextu.model.UserEntity
import com.app.juawcevada.vostextu.shared.AppDispatchers
import dagger.Reusable
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Reusable
class GetUserUseCase @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val usersRepository: UsersRepository
) : UseCase<Long, UserEntity>() {

    override suspend fun execute(parameters: Long): Try<UserEntity> = withContext(appDispatchers.Default) {
        usersRepository.getUsers().flatMap { Try { it.single { users -> users.id == parameters } } }
    }
}