package com.torchdragon.gish.model.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.torchdragon.gish.api.GitHubApi
import kotlinx.coroutines.CoroutineScope

class IssuesDataSourceFactory(
    private val initialUrl: String,
    private val state: LiveData<IssueState>,
    private val githubApi: GitHubApi,
    private val scope: CoroutineScope
) : DataSource.Factory<String, GitHubIssue>() {

    private val _dataSource = MutableLiveData<IssuesDataSource>()

    val dataSource: LiveData<IssuesDataSource> = _dataSource

    override fun create(): DataSource<String, GitHubIssue> {
        return IssuesDataSource(initialUrl, state.value ?: IssueState.open, githubApi, scope).also {
            _dataSource.postValue(it)
        }
    }
}
