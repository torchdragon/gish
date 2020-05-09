package com.torchdragon.gish.model.repositories

import androidx.recyclerview.widget.DiffUtil

data class GitRepository(val name: String) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitRepository>() {
            override fun areItemsTheSame(oldItem: GitRepository, newItem: GitRepository): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: GitRepository,
                newItem: GitRepository
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}