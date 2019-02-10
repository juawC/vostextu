package com.app.juawcevada.vostextu.model

data class CommentEntity(
    val body: String,
    val email: String,
    val id: Long,
    val name: String,
    val postId: Long
)