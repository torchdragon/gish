package com.torchdragon.gish.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.model.issues.GitHubIssue
import com.torchdragon.gish.model.issues.IssuesDataSourceFactory
import java.util.concurrent.Executors

class IssuesViewModel(gitHubApi: GitHubApi,
                      initialUrl: String) : ViewModel() {

    val issuesApiData: LiveData<PagedList<GitHubIssue>> =
        LivePagedListBuilder(
            IssuesDataSourceFactory(initialUrl, gitHubApi, viewModelScope),
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitHubApi.ITEMS_PER_PAGE)
                .setPrefetchDistance(GitHubApi.ITEMS_PER_PAGE * 2)
                .build())
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
}
