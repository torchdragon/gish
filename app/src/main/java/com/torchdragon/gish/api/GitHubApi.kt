package com.torchdragon.gish.api

import com.torchdragon.gish.model.issues.GitHubIssue
import com.torchdragon.gish.model.repositories.GitHubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val ORG_REPOSITORIES_PATH = "orgs/{org}/repos"
        const val ITEMS_PER_PAGE = 100
    }

    @GET
    fun repositories(@Url pagingUrl: String, @Query("per_page") itemsPerPage: Int = ITEMS_PER_PAGE): Call<List<GitHubRepository>>

    @GET
    fun issues(@Url pagingUrl: String, @Query("per_page") itemsPerPage: Int = ITEMS_PER_PAGE): Call<List<GitHubIssue>>
}
