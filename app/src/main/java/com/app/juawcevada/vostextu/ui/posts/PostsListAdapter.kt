package com.app.juawcevada.vostextu.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.app.juawcevada.vostextu.R
import com.app.juawcevada.vostextu.databinding.PostItemBinding
import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.ui.shared.ListRecyclerAdapter

class PostsListAdapter(
    private val openDetail: (PostEntity) -> Unit
) : ListRecyclerAdapter<PostEntity, PostItemBinding>(PostDiff()), PostItemActions {

    override fun bind(binding: PostItemBinding, item: PostEntity, position: Int, payloads: MutableList<Any>) {
        binding.item = item
        binding.actions = this
    }

    override fun createBinding(parent: ViewGroup): PostItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(
            layoutInflater,
            R.layout.post_item,
            parent,
            false
        )
    }

    class PostDiff : DiffUtil.ItemCallback<PostEntity>() {
        override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
            return oldItem == newItem
        }

    }

    override fun openDetail(item: PostEntity) {
        openDetail(item)
    }
}