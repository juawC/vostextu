package com.app.juawcevada.vostextu.ui.posts

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.juawcevada.vostextu.model.PostEntity

@BindingAdapter("setPosts")
fun setPosts(recyclerView: RecyclerView, list: List<PostEntity>?) {
    (recyclerView.adapter as PostsListAdapter).submitList(list)
}