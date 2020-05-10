package com.torchdragon.gish.model.repositories

import androidx.paging.PageKeyedDataSource
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.api.parseLink

class RepositoriesDataSource(
    private val org: String,
    private val githubApi: GitHubApi
) : PageKeyedDataSource<String, GitRepository>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GitRepository>
    ) {
        val response = githubApi.repositories(GitHubApi.ORG_REPOSITORIES_PATH.replace("{org}", org)).execute()

        if (response.isSuccessful) {
            val link = response.headers()["link"].parseLink()
            callback.onResult(response.body() ?: listOf(), null, link.next)
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitRepository>
    ) {
        val response = githubApi.repositories(params.key).execute()

        if (response.isSuccessful) {
            val link = response.headers()["link"].parseLink()
            callback.onResult(response.body() ?: listOf(), link.next)
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitRepository>
    ) {
        // NO-OP, we'll always be loading from Page 1.
    }
}
