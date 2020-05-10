package com.torchdragon.gish.model.repositories

import androidx.paging.PageKeyedDataSource

class RepositoriesDataSource : PageKeyedDataSource<String, GitRepository>() {

    private val max = 1000

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GitRepository>
    ) {
        val data = List(params.requestedLoadSize) { i -> GitRepository("Repo #$i") }
        callback.onResult(data, 0, max, null, "${params.requestedLoadSize}")
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitRepository>
    ) {
        val key = params.key.toInt()

        val data = List(params.requestedLoadSize) { i -> GitRepository("Repo #${i + key}") }

        val nextKey = when {
            key + params.requestedLoadSize >= max -> null
            else -> "${key + params.requestedLoadSize}"
        }

        callback.onResult(data, nextKey)
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitRepository>
    ) {
        // NO-OP, we'll always be loading from Page 1.
    }
}
