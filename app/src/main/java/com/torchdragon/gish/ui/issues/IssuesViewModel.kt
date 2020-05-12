package com.torchdragon.gish.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.model.issues.GitHubIssue
import com.torchdragon.gish.model.issues.IssueState
import com.torchdragon.gish.model.issues.IssuesDataSource
import com.torchdragon.gish.model.issues.IssuesDataSourceFactory
import java.util.concurrent.Executors

class IssuesViewModel(gitHubApi: GitHubApi,
                      initialUrl: String) : ViewModel() {

    private val _filterData = MutableLiveData<IssueState>().apply {
        value = IssueState.open
    }

    val filterData: LiveData<IssueState> = _filterData

    fun updateFilter(state: IssueState) {
        if (_filterData.value != state) {
            _filterData.value = state
        }
        issuesSource.value?.invalidate()
    }

    private val issuesSource: LiveData<IssuesDataSource>
    val issuesApiData: LiveData<PagedList<GitHubIssue>>

    init {
        val factory = IssuesDataSourceFactory(initialUrl, _filterData, gitHubApi, viewModelScope).also {
            issuesSource = it.dataSource
        }
        issuesApiData = LivePagedListBuilder(factory,
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitHubApi.ITEMS_PER_PAGE)
                .setPrefetchDistance(GitHubApi.ITEMS_PER_PAGE * 2)
                .build())
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
    }
}
