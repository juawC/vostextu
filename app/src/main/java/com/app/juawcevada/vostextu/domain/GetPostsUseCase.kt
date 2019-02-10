package com.app.juawcevada.vostextu.domain

import arrow.core.Try
import com.app.juawcevada.vostextu.data.PostsRepository
import com.app.juawcevada.vostextu.domain.shared.UseCase
import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.shared.AppDispatchers
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) : UseCase<Unit, List<PostEntity>>() {

    override suspend fun execute(parameters: Unit): Try<List<PostEntity>> {
        return postsRepository.getPosts()
    }
}