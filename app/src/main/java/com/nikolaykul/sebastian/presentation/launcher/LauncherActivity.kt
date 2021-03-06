package com.nikolaykul.sebastian.presentation.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.presentation.feed.list.FeedListActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        // navigate & finish
        startActivity(FeedListActivity.startIntent(this))
        finish()
    }

}
