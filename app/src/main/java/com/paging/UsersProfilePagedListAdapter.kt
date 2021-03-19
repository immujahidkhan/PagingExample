package com.paging

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.paging.databinding.RowItemPostBinding
import com.paging.models.PostsItem


class UsersProfilePagedListAdapter(
    private val context: Activity,
) :
    PagedListAdapter<PostsItem, UsersProfilePagedListAdapter.ProfileItemViewHolder>(
        ProfilesDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val binding: RowItemPostBinding =
            RowItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        //timberLog.i("currentList:" + currentList.toString())
        val data = getItem(position)
        if (data != null) {
            holder.binding.title.text = data.title.rendered
        }
    }

    class ProfilesDiffCallback : DiffUtil.ItemCallback<PostsItem>() {
        override fun areItemsTheSame(
            oldItem: PostsItem,
            newItem: PostsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostsItem,
            newItem: PostsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ProfileItemViewHolder(val binding: RowItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object {
        private const val TAG = "UsersAd"
    }
}