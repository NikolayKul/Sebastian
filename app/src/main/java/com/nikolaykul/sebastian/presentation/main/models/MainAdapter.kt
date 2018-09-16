package com.nikolaykul.sebastian.presentation.main.models

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nikolaykul.sebastian.presentation.feed.list.FeedListFragment

class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        2 -> FeedListFragment.newInstance()
        else -> FeedListFragment.newInstance()  // TODO: replace with `null` when there're more pages
    }

    override fun getCount() = 1
}