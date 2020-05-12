package com.torchdragon.gish.model.issues

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubIssue(val title: String, val state: IssueState) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitHubIssue>() {
            override fun areItemsTheSame(oldItem: GitHubIssue, newItem: GitHubIssue): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: GitHubIssue, newItem: GitHubIssue): Boolean {
                return oldItem == newItem
            }
        }
    }
}
