package com.torchdragon.gish.model.issues

import androidx.paging.PageKeyedDataSource
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.api.parseLink

class IssuesDataSource(
    private val initialUrl: String,
    private val gitHubApi: GitHubApi
) : PageKeyedDataSource<String, GitHubIssue>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GitHubIssue>
    ) {
        val response = gitHubApi.issues(initialUrl, GitHubApi.ITEMS_PER_PAGE).execute()

        if (response.isSuccessful) {
            val link = response.parseLink()
            callback.onResult(response.body() ?: listOf(), null, link.next)
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubIssue>
    ) {
        val response = gitHubApi.issues(params.key).execute()

        if (response.isSuccessful) {
            val link = response.parseLink()
            callback.onResult(response.body() ?: listOf(), link.next)
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubIssue>
    ) {
        // NO-OP, we'll always be loading from Page 1.
    }
}
