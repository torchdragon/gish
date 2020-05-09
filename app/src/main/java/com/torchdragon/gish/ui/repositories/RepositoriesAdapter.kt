package com.torchdragon.gish.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.torchdragon.gish.BR
import com.torchdragon.gish.R
import com.torchdragon.gish.databinding.RepositoryItemBinding
import com.torchdragon.gish.model.repositories.GitRepository

class RepositoriesAdapter: ListAdapter<GitRepository, RepositoriesAdapter.RepositoryViewHolder>(GitRepository.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding: RepositoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.repository_item, parent, false)

        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepositoryViewHolder(private val binding: RepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        internal fun bind(repository: GitRepository) {
            binding.setVariable(BR.repoItemModel, repository)
            binding.executePendingBindings()
        }
    }
}
