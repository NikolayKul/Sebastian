package com.nikolaykul.sebastian.presentation.feed.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityFeedListBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import com.nikolaykul.sebastian.presentation.feed.list.adapter.FeedListAdapter
import com.nikolaykul.sebastian.presentation.feed.list.adapter.FeedListViewItem
import timber.log.Timber

class FeedListActivity : BaseActivity<ActivityFeedListBinding>() {
    companion object {
        fun startIntent(context: Context) = Intent(context, FeedListActivity::class.java)
    }

    private val viewModel by viewModelDelegate<FeedListViewModel>()
    private val adapter = FeedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initListeners()
        viewModel.observeState().easySubscribe(this::setState)
    }

    override fun getLayoutId() = R.layout.activity_feed_list

    private fun setState(state: FeedListState) {
        val loadingStub = if (state.isLoading) "Show loading" else "Hide loading"
        Timber.d(loadingStub)

        state.error?.also { Timber.d("Show error: $it") }

        state.feeds?.also(this::setItems)
    }

    private fun setItems(feeds: List<RssFeed>) {
        feeds.map { FeedListViewItem(it, viewModel::onFeedClicked) }
                .also(adapter::setItems)
    }

    private fun initListeners() {
        binding.fab.setOnClickListener { viewModel.loadChannel() }
    }

    private fun initRecyclerView() {
        binding.rvFeeds.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

}