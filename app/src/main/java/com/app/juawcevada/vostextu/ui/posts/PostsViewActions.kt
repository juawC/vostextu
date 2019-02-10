package com.app.juawcevada.vostextu.ui.posts

import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.ui.shared.LceViewActions

interface PostsViewActions : LceViewActions {
    fun openDetail(item: PostEntity)
}