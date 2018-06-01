package com.nikolaykul.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityMainBinding
import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.SCREEN_FEED_LIST
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    // TODO: move to the ViewModel
    @Inject lateinit var router: Router

    override val layoutResId get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navView.setOnNavigationItemSelectedListener(NavigationListener())
        binding.navView.selectedItemId = R.id.action_feeds
    }

    override fun provideNavigator() = BaseNavigator(this, binding.container.id)

    private inner class NavigationListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
            R.id.action_feeds -> {
                router.navigateTo(SCREEN_FEED_LIST)
                true
            }
            else -> true
        }
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
