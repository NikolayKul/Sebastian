package com.nikolaykul.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityMainBinding
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import com.nikolaykul.sebastian.presentation.feed.list.FeedListFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResId get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navView.setOnNavigationItemSelectedListener(NavigationListener())
    }

    private inner class NavigationListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
            R.id.action_feeds -> {
                showFeeds()
                true
            }
            else -> true
        }
    }

    private fun showFeeds() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FeedListFragment.newInstance())
            .commit()
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}