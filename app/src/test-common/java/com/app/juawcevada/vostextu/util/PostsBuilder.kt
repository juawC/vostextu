package com.app.juawcevada.vostextu.util

import com.app.juawcevada.vostextu.model.PostEntity

@PostsDsl
class PostsBuilder {
    private var userId: Long = 0
    private var id: Long = 0
    private var title: String = ""
    private var body: String = ""

    fun userId(body: () -> Long) {
        userId = body()
    }

    fun id(body: () -> Long) {
        id = body()
    }

    fun title(body: () -> String) {
        title = body()
    }

    fun body(bodyFun: () -> String) {
        body = bodyFun()
    }

    fun build() = PostEntity(
        userId,
        id,
        title,
        body
    )
}

fun post(body: PostsBuilder.() -> Unit) = PostsBuilder().apply(body).build()