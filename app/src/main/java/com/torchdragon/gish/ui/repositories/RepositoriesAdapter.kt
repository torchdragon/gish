package com.torchdragon.gish.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.torchdragon.gish.BR
import com.torchdragon.gish.R
import com.torchdragon.gish.databinding.RepositoryItemBinding
import com.torchdragon.gish.model.repositories.GitHubRepository

class RepositoriesAdapter(private val navigationHandler: IssueNavigationHandler)
    : PagedListAdapter<GitHubRepository, RepositoriesAdapter.RepositoryViewHolder>(GitHubRepository.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding: RepositoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.repository_item, parent, false)

        return RepositoryViewHolder(binding, navigationHandler)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        when(val item = getItem(position)) {
            null -> holder.missing()
            else -> holder.bind(item)
        }
    }

    class RepositoryViewHolder(private val binding: RepositoryItemBinding, private val navigationHandler: IssueNavigationHandler): RecyclerView.ViewHolder(binding.root) {
        internal fun bind(repository: GitHubRepository) {
            binding.setVariable(BR.repoItemModel, repository)
            binding.setVariable(BR.repoHandlers, navigationHandler)
            binding.executePendingBindings()
        }

        internal fun missing() {
            binding.setVariable(BR.repoItemModel, GitHubRepository("Loading...", ""))
            binding.executePendingBindings()
        }
    }
}
