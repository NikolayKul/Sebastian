package com.nikolaykul.sebastian.presentation.feed.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityFeedDetailsBinding
import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import timber.log.Timber


class FeedDetailsActivity : BaseActivity<ActivityFeedDetailsBinding>() {

    private val viewModel by lazyViewModelDelegate<FeedDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observeState()
            .easySubscribe { Timber.d("FeedId from VM is $it") }
    }

    override fun getLayoutId() = R.layout.activity_feed_details

    companion object {
        fun startIntent(context: Context, feedId: String): Intent {
            return Intent(context, FeedDetailsActivity::class.java)
                .putExtra(EXTRA_FEED_ID, feedId)
        }
    }
}

private const val EXTRA_FEED_ID = "extra_feed_id"

val FeedDetailsActivity.feedId: FeedId
    get() {
        val id = intent.extras[EXTRA_FEED_ID] as String
        return FeedId(id)
    }
