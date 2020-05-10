package com.torchdragon.gish.model.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.torchdragon.gish.api.GitHubApi

class RepositoriesDataSourceFactory(
    val org: String,
    val githubApi: GitHubApi
) : DataSource.Factory<String, GitRepository>() {

    private val _dataSource = MutableLiveData<RepositoriesDataSource>()

    override fun create(): DataSource<String, GitRepository> {
        return RepositoriesDataSource(org, githubApi).also {
            _dataSource.postValue(it)
        }
    }
}
