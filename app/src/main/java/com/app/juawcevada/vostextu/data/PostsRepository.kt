package com.app.juawcevada.vostextu.data

import arrow.core.Try
import com.app.juawcevada.vostextu.model.PostEntity
import dagger.Reusable
import ru.gildor.coroutines.retrofit.await
import javax.inject.Inject

@Reusable
class PostsRepository @Inject constructor(
    private val userService: ApiService
) {

    suspend fun getPosts(): Try<List<PostEntity>> = Try {
        userService.getPosts().await()
    }
}