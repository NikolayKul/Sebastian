package com.nikolaykul.sebastian.presentation.main

import android.arch.lifecycle.ViewModel
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import com.nikolaykul.sebastian.utils.rx.RxRelay
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : ViewModel() {

    private val loadingRelay = RxRelay<Boolean>()
    private val feedsRelay = RxRelay<List<RssFeed>>()

    fun observeLoading() = loadingRelay.observe()

    fun observeFeeds() = feedsRelay.observe()

    fun loadChannel() {
        launch(UI) {
            loadingRelay.push(true)
            val channel = getChannelUseCase.execute()
            feedsRelay.push(channel.feeds)
            loadingRelay.push(false)
        }
    }

}
