package com.torchdragon.gish.model.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class RepositoriesDataSourceFactory : DataSource.Factory<String, GitRepository>() {

    private val _dataSource = MutableLiveData<RepositoriesDataSource>()

    override fun create(): DataSource<String, GitRepository> {
        return RepositoriesDataSource().also {
            _dataSource.postValue(it)
        }
    }
}
