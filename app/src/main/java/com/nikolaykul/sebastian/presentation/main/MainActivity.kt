package com.nikolaykul.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityMainBinding
import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResId get() = R.layout.activity_main

    private val viewModel by viewModelDelegate<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    override fun provideNavigator() = BaseNavigator(this, binding.container.id)

    private fun initNavigation() {
        with(binding.navView) {
            setOnNavigationItemSelectedListener(::onNavigationItemSelected)
            selectedItemId = R.id.action_feeds
        }
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_feeds -> viewModel.onFeedListClicked()
        }
        return true
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
