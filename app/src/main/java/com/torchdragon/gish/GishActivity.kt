package com.torchdragon.gish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.torchdragon.gish.ui.repositories.RepositoriesFragment

class GishActivity : AppCompatActivity() {
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
