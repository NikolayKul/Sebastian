package com.nikolaykul.sebastian.presentation

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsActivity
import ru.terrakok.cicerone.android.SupportAppNavigator

const val SCREEN_FEED_DETAILS = "screen_feed_details"

class BaseNavigator(
    activity: FragmentActivity,
    fragmentContainerId: Int? = null
) : SupportAppNavigator(activity, fragmentContainerId ?: 0) {

    override fun createActivityIntent(
        context: Context,
        screenKey: String,
        data: Any?
    ) = when (screenKey) {
        SCREEN_FEED_DETAILS -> FeedDetailsActivity.getStartIntent(context, data as String)
        else -> throw IllegalStateException("Unknown screen key: $screenKey")
    }

    override fun createFragment(screenKey: String, data: Any?): Fragment {
        TODO("not implemented")
    }

}