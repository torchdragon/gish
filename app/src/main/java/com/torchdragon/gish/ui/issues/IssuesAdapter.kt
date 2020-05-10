package com.torchdragon.gish.ui.issues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.torchdragon.gish.BR
import com.torchdragon.gish.R
import com.torchdragon.gish.databinding.IssueItemBinding
import com.torchdragon.gish.model.issues.GitHubIssue

class IssuesAdapter: PagedListAdapter<GitHubIssue, IssuesAdapter.RepositoryViewHolder>(GitHubIssue.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding: IssueItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.issue_item, parent, false)

        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        when(val item = getItem(position)) {
            null -> holder.missing()
            else -> holder.bind(item)
        }
    }

    class RepositoryViewHolder(private val binding: IssueItemBinding): RecyclerView.ViewHolder(binding.root) {
        internal fun bind(issue: GitHubIssue) {
            binding.setVariable(BR.issueItemModel, issue)
            binding.executePendingBindings()
        }

        internal fun missing() {
            binding.setVariable(BR.issueItemModel, GitHubIssue("Loading...", ""))
            binding.executePendingBindings()
        }
    }
}
