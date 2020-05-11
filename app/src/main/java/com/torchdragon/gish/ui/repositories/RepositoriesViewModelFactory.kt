package com.torchdragon.gish.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.torchdragon.gish.api.GitHubApi

class RepositoriesViewModelFactory(private val gitHubApi: GitHubApi) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepositoriesViewModel::class.java)) {
            return RepositoriesViewModel(gitHubApi) as T
        }

        throw IllegalArgumentException("Unknown VieModel class")
    }
}
