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
import com.nikolaykul.sebastian.utils.common.viewModelFactoryProviderDelegate
import com.nikolaykul.sebastian.utils.view.setVisible
import com.nikolaykul.sebastian.utils.vm.ViewModelCommonFactory
import timber.log.Timber
import javax.inject.Inject

class FeedListFragment : BaseFragment<FragmentFeedListBinding>() {
    override val layoutResId = R.layout.fragment_feed_list

    @Inject lateinit var viewModelFactory: ViewModelCommonFactory
    private val viewModel by viewModelFactoryProviderDelegate<FeedListViewModel> { viewModelFactory }
    private val feedAdapter = FeedListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        viewModel.observeState().easySubscribe(this::setState)
        viewModel.loadChannel()
    }

    private fun setState(state: FeedListState) {
        binding.loader.setVisible(state.isLoading)

        state.error?.also { Timber.d("Show error: $it") }

        state.feeds?.also(this::setItems)
    }

    private fun setItems(feeds: List<RssFeed>) {
        feeds.map { FeedListViewItem(it, viewModel::onFeedClicked) }
            .also(feedAdapter::setItems)
    }

    private fun initRecyclerView() {
        with(binding.rvFeeds) {
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
        }
    }

    companion object {
        fun newInstance() = FeedListFragment()
    }
}