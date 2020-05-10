package com.torchdragon.gish.model.repositories

import androidx.recyclerview.widget.DiffUtil
import com.helpchoice.kotlin.urlet.impl.UriTemplate
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubRepository(val name: String,
                            @Json(name = "issues_url") val issuesRef: String) {

    val issuesLink: String get() = UriTemplate(issuesRef).toURL(mapOf()).toString()

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitHubRepository>() {
            override fun areItemsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean {
                return oldItem.issuesRef == newItem.issuesRef
            }

            override fun areContentsTheSame(
                oldItem: GitHubRepository,
                newItem: GitHubRepository
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
