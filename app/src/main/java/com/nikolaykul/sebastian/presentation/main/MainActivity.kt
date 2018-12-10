package com.nikolaykul.sebastian.presentation.main

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityMainBinding
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import com.nikolaykul.sebastian.presentation.main.models.MainAdapter
import com.nikolaykul.sebastian.utils.common.viewModelFactoryProviderDelegate
import com.nikolaykul.sebastian.utils.view.get
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResId get() = R.layout.activity_main

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModelFactoryProviderDelegate<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
        with(binding) {
            viewPager.adapter = MainAdapter(supportFragmentManager)
            viewPager.addOnPageChangeListener(PageListener())

            navView.setOnNavigationItemSelectedListener(NavigationListener())

            // start page
            navView.selectedItemId = R.id.action_feeds
        }
    }

    private inner class PageListener : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            binding.navView.menu[position].isChecked = true
        }
    }

    private inner class NavigationListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            binding.viewPager.currentItem = when (item.itemId) {
                R.id.action_search -> 0
                R.id.action_channels -> 1
                R.id.action_feeds -> 2
                else -> -1
            }
            return true
        }
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
