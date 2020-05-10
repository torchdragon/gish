package com.torchdragon.gish.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.torchdragon.gish.model.repositories.GitRepository
import com.torchdragon.gish.model.repositories.RepositoriesDataSourceFactory
import java.util.concurrent.Executors

class RepositoriesViewModel : ViewModel() {

    val repositoriesData: LiveData<PagedList<GitRepository>> =
        LivePagedListBuilder(RepositoriesDataSourceFactory(), 20)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
}
