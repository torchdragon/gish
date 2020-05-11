package com.torchdragon.gish.model.repositories

import androidx.paging.PageKeyedDataSource
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.api.parseLink

class RepositoriesDataSource(
    private val org: String,
    private val gitHubApi: GitHubApi
) : PageKeyedDataSource<String, GitHubRepository>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GitHubRepository>
    ) {
        val response = gitHubApi.repositories(GitHubApi.ORG_REPOSITORIES_PATH.replace("{org}", org), GitHubApi.ITEMS_PER_PAGE).execute()

        if (response.isSuccessful) {
            val link = response.parseLink()
            callback.onResult(response.body() ?: listOf(), null, link.next)
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubRepository>
    ) {
        val response = gitHubApi.repositories(params.key).execute()

        if (response.isSuccessful) {
            val link = response.parseLink()
            callback.onResult(response.body() ?: listOf(), link.next)
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubRepository>
    ) {
        // NO-OP, we'll always be loading from Page 1.
    }
}
