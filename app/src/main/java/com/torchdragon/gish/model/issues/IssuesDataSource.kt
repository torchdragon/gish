package com.torchdragon.gish.model.issues

import androidx.paging.PageKeyedDataSource
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.api.parseLink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class IssuesDataSource(
    private val initialUrl: String,
    private val state: IssueState,
    private val gitHubApi: GitHubApi,
    private val scope: CoroutineScope
) : PageKeyedDataSource<String, GitHubIssue>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GitHubIssue>
    ) {
        scope.launch {
            val response = gitHubApi.issues(initialUrl, state.type, GitHubApi.ITEMS_PER_PAGE)

            if (response.isSuccessful) {
                val link = response.parseLink()
                callback.onResult(response.body() ?: listOf(), null, link.next)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubIssue>
    ) {
        scope.launch {
            val response = gitHubApi.issues(params.key)

            if (response.isSuccessful) {
                val link = response.parseLink()
                callback.onResult(response.body() ?: listOf(), link.next)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubIssue>
    ) {
        // NO-OP, we'll always be loading from Page 1.
    }
}
