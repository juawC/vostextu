package com.app.juawcevada.vostextu.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.juawcevada.vostextu.domain.GetPostsUseCase
import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.shared.AppDispatchers
import com.app.juawcevada.vostextu.shared.Event
import com.app.juawcevada.vostextu.testing.OpenClassOnDebug
import com.app.juawcevada.vostextu.ui.shared.ScopedViewModel
import com.app.juawcevada.vostextu.ui.shared.ViewStateLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenClassOnDebug
class PostsViewModel @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val getPostsUseCase: GetPostsUseCase
) : ScopedViewModel(), PostsViewActions {

    private val _viewState = ViewStateLiveData(PostsViewState())
    val viewState: LiveData<PostsViewState>
        get() = _viewState

    private val _navigationAction = MutableLiveData<Event<PostsNavigationActions>>()
    val navigationAction: LiveData<Event<PostsNavigationActions>>
        get() = _navigationAction

    init {
        loadPosts()
    }

    override fun openDetail(item: PostEntity) {
        _navigationAction.value = Event(PostsNavigationActions.OpenDetail(item))
    }

    override fun retry() {
        loadPosts()
    }

    private final fun loadPosts() {
        launch(appDispatchers.Main) {
            listLoading()
            getPostsUseCase(Unit).fold({
                listError(it)
            }, {
                listSuccess(it)
            })
        }
    }

    fun listSuccess(list: List<PostEntity>) {
        _viewState.updateState {
            copy(
                isLoading = false,
                list = list,
                errorMessage = null
            )
        }
    }

    fun listError(error: Throwable) {
        _viewState.updateState {
            copy(
                isLoading = false,
                list = emptyList(),
                errorMessage = error.toString()
            )
        }
    }

    fun listLoading() {
        _viewState.updateState {
            copy(
                isLoading = true,
                list = emptyList(),
                errorMessage = null
            )
        }
    }
}