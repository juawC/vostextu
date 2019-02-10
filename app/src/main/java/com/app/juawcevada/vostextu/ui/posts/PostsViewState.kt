package com.app.juawcevada.vostextu.ui.posts

import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.ui.shared.LceViewState

data class PostsViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: String? = null,
    val list: List<PostEntity> = emptyList()
)  : LceViewState