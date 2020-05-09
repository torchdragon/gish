package com.torchdragon.gish.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.torchdragon.gish.model.repositories.GitRepository

class RepositoriesViewModel : ViewModel() {

    private val _repositoriesData = MutableLiveData<List<GitRepository>>()

    val repositoriesData get() = _repositoriesData as LiveData<List<GitRepository>>

    fun load() {
        _repositoriesData.value = listOf(GitRepository("Mine"), GitRepository("Yours"))
    }
}
