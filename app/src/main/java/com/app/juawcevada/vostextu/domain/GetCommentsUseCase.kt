package com.app.juawcevada.vostextu.domain

import arrow.core.Try
import com.app.juawcevada.vostextu.data.CommentsRepository
import com.app.juawcevada.vostextu.domain.shared.UseCase
import com.app.juawcevada.vostextu.model.CommentEntity
import com.app.juawcevada.vostextu.shared.AppDispatchers
import dagger.Reusable
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Reusable
class GetCommentsUseCase @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val commentsRepository: CommentsRepository
) : UseCase<Long, List<CommentEntity>>() {

    override suspend fun execute(parameters: Long): Try<List<CommentEntity>> = withContext(appDispatchers.Default){
        commentsRepository.getComments().map { it.filter { comments -> comments.postId == parameters } }
    }
}