package com.torchdragon.gish.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.model.repositories.GitHubRepository
import com.torchdragon.gish.model.repositories.RepositoriesDataSourceFactory
import java.util.concurrent.Executors

class RepositoriesViewModel(gitHubApi: GitHubApi) : ViewModel() {

    val repositoriesData: LiveData<PagedList<GitHubRepository>> =
        LivePagedListBuilder(RepositoriesDataSourceFactory("google", gitHubApi, viewModelScope),
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitHubApi.ITEMS_PER_PAGE)
                .setPrefetchDistance(GitHubApi.ITEMS_PER_PAGE * 2)
                .build())
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
}
