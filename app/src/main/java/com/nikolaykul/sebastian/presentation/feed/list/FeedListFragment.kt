package com.nikolaykul.sebastian.presentation.feed.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.FragmentFeedListBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.BaseFragment
import com.nikolaykul.sebastian.presentation.feed.list.adapter.FeedListAdapter
import com.nikolaykul.sebastian.presentation.feed.list.adapter.FeedListViewItem
import timber.log.Timber

class FeedListFragment : BaseFragment<FragmentFeedListBinding>() {
    override val layoutResId get() = R.layout.fragment_feed_list

    private val viewModel by viewModelDelegate<FeedListViewModel>()
    private val adapter = FeedListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        initListeners()
        viewModel.observeState().easySubscribe(this::setState)
    }

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
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
    }

    companion object {
        fun newInstance() = FeedListFragment()
    }
}