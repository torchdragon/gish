package com.torchdragon.gish.model.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.torchdragon.gish.api.GitHubApi
import kotlinx.coroutines.CoroutineScope

class RepositoriesDataSourceFactory(
    private val org: String,
    private val githubApi: GitHubApi,
    private val scope: CoroutineScope
) : DataSource.Factory<String, GitHubRepository>() {

    private val _dataSource = MutableLiveData<RepositoriesDataSource>()

    override fun create(): DataSource<String, GitHubRepository> {
        return RepositoriesDataSource(org, githubApi, scope).also {
            _dataSource.postValue(it)
        }
    }
}
