package com.torchdragon.gish.ui.repositories

import com.torchdragon.gish.model.repositories.GitHubRepository

interface IssueNavigationHandler {
    fun navigateTo(destination: GitHubRepository)
}
