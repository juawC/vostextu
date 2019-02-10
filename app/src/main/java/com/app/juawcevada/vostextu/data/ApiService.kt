package com.app.juawcevada.vostextu.data

import com.app.juawcevada.vostextu.model.CommentEntity
import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.model.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<PostEntity>>

    @GET("users")
    fun getUsers(): Call<List<UserEntity>>

    @GET("comments")
    fun getComments(): Call<List<CommentEntity>>

}