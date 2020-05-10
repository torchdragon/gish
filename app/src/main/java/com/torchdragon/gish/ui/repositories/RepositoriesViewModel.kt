package com.torchdragon.gish.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.model.repositories.GitRepository
import com.torchdragon.gish.model.repositories.RepositoriesDataSourceFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors

class RepositoriesViewModel : ViewModel() {

    private val githubApi = Retrofit.Builder()
        .baseUrl(GitHubApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)

    val repositoriesData: LiveData<PagedList<GitRepository>> =
        LivePagedListBuilder(RepositoriesDataSourceFactory("google", githubApi),
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitHubApi.ITEMS_PER_PAGE)
                .setPrefetchDistance(GitHubApi.ITEMS_PER_PAGE * 2)
                .build())
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
}
