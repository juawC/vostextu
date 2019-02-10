package com.app.juawcevada.vostextu.data

import arrow.core.Try
import com.app.juawcevada.vostextu.model.CommentEntity
import dagger.Reusable
import ru.gildor.coroutines.retrofit.await
import javax.inject.Inject

@Reusable
class CommentsRepository @Inject constructor(
    private val userService: ApiService
) {

    suspend fun getComments(): Try<List<CommentEntity>> = Try {
        userService.getComments().await()
    }
}