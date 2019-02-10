package com.app.juawcevada.vostextu.ui.posts

import com.app.juawcevada.vostextu.model.PostEntity

sealed class PostsNavigationActions {
    data class OpenDetail(val item: PostEntity) : PostsNavigationActions()
}