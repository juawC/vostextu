package com.app.juawcevada.vostextu.ui.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.juawcevada.vostextu.R
import com.app.juawcevada.vostextu.di.annotation.PostDetail
import com.app.juawcevada.vostextu.domain.GetCommentsUseCase
import com.app.juawcevada.vostextu.domain.GetUserUseCase
import com.app.juawcevada.vostextu.model.CommentEntity
import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.model.UserEntity
import com.app.juawcevada.vostextu.shared.AppDispatchers
import com.app.juawcevada.vostextu.shared.Event
import com.app.juawcevada.vostextu.shared.SnackbarMessage
import com.app.juawcevada.vostextu.testing.OpenClassOnDebug
import com.app.juawcevada.vostextu.ui.shared.ScopedViewModel
import com.app.juawcevada.vostextu.ui.shared.ViewStateLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenClassOnDebug
class PostDetailViewModel @Inject constructor(
    @PostDetail private val post: PostEntity,
    private val appDispatchers: AppDispatchers,
    private val commentsUseCase: GetCommentsUseCase,
    private val userUseCase: GetUserUseCase
) : ScopedViewModel() {


    private val _viewState = ViewStateLiveData(
        PostDetailViewState(
            title = post.title,
            body = post.body
        )
    )
    val viewState: LiveData<PostDetailViewState>
        get() = _viewState

    private val _errorMessage = MutableLiveData<Event<SnackbarMessage>>()
    val errorMessage: LiveData<Event<SnackbarMessage>>
        get() = _errorMessage

    init {
        launch(appDispatchers.Main) {
            commentsUseCase(post.id).fold(
                {
                    loadCommentError()
                },{
                    loadCommentSuccess(it)
                })

            userUseCase(post.userId).fold(
                {
                    loadUserError()
                },{
                    loadUserSuccess(it)
                })
        }
    }

    private fun loadCommentSuccess(comments: List<CommentEntity>) {
        _viewState.updateState {
            copy(numberOfComments = comments.size )
        }
    }

    private fun loadCommentError() {
        _errorMessage.value = Event(SnackbarMessage(R.string.oops_comments))
    }

    private fun loadUserSuccess(user: UserEntity) {
        _viewState.updateState {
            copy(userName = user.name )
        }
    }

    private fun loadUserError() {
        _errorMessage.value = Event(SnackbarMessage(R.string.oops_user))
    }

}