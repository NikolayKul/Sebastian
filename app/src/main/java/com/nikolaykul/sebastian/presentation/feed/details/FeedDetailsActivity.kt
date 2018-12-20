package com.nikolaykul.sebastian.presentation.feed.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityFeedDetailsBinding
import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import com.nikolaykul.sebastian.presentation.utils.ext.viewModelFactoryDelegate
import timber.log.Timber
import javax.inject.Inject

private const val EXTRA_FEED_ID = "extra_feed_id"

class FeedDetailsActivity : BaseActivity<ActivityFeedDetailsBinding>() {
    override val layoutResId = R.layout.activity_feed_details

    @Inject lateinit var viewModelFactory: FeedDetailsViewModel.Factory
    private val viewModel by viewModelFactoryDelegate<FeedDetailsViewModel> {
        val id = intent.extras[EXTRA_FEED_ID] as String
        viewModelFactory.create(FeedId(id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observeState()
            .easySubscribe { Timber.d("FeedId from VM is $it") }
        viewModel.loadFeed()
    }

    companion object {
        fun getStartIntent(context: Context, feedId: String): Intent {
            return Intent(context, FeedDetailsActivity::class.java)
                .putExtra(EXTRA_FEED_ID, feedId)
        }
    }
}
