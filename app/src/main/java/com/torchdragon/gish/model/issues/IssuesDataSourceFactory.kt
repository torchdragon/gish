package com.torchdragon.gish.model.issues

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.torchdragon.gish.api.GitHubApi
import kotlinx.coroutines.CoroutineScope

class IssuesDataSourceFactory(
    private val initialUrl: String,
    private val githubApi: GitHubApi,
    private val scope: CoroutineScope
) : DataSource.Factory<String, GitHubIssue>() {

    private val _dataSource = MutableLiveData<IssuesDataSource>()

    override fun create(): DataSource<String, GitHubIssue> {
        return IssuesDataSource(initialUrl, githubApi, scope).also {
            _dataSource.postValue(it)
        }
    }
}
