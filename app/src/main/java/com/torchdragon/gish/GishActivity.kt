package com.torchdragon.gish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.torchdragon.gish.api.GitHubApi
import com.torchdragon.gish.ui.repositories.RepositoriesFragment
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GishActivity : AppCompatActivity() {

    val gitHubApi: GitHubApi by lazy {
        val client = OkHttpClient.Builder()
            .cache(Cache(cacheDir, 25 * 1024 * 1024)) // 25mb cache just to be sure
            .build()

        Retrofit.Builder()
            .baseUrl(GitHubApi.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gish_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RepositoriesFragment.newInstance(), RepositoriesFragment.TAG)
                    .commitNow()
        }
    }
}
