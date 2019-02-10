package com.app.juawcevada.vostextu.ui.postdetail

data class PostDetailViewState(
    val isUserLoading: Boolean = true,
    val isCommentsLoading: Boolean = true,
    val title: String = "",
    val body: String = "",
    val userName: String? = null,
    val numberOfComments: Int? = null
)