package com.torchdragon.gish.ui.issues

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IssuesViewModelFactory(private val args: Bundle) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IssuesViewModel::class.java)) {
            val initialUrl = args.getString(IssuesFragment.URL_EXTRA)
                ?: throw IllegalArgumentException("Initial Url can not be null")
            return IssuesViewModel(initialUrl) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
