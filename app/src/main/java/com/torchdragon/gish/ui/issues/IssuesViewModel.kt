package com.torchdragon.gish.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.model.issues.GitHubIssue
import com.torchdragon.gish.model.issues.IssuesDataSourceFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors

class IssuesViewModel(initialUrl: String) : ViewModel() {

    private val githubApi = Retrofit.Builder()
        .baseUrl(GitHubApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)

    val issuesData: LiveData<PagedList<GitHubIssue>> =
        LivePagedListBuilder(
            IssuesDataSourceFactory(initialUrl, githubApi),
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitHubApi.ITEMS_PER_PAGE)
                .setPrefetchDistance(GitHubApi.ITEMS_PER_PAGE * 2)
                .build())
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
}
