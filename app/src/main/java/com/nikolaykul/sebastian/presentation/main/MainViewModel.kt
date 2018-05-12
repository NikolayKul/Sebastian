package com.nikolaykul.sebastian.presentation.main

import com.nikolaykul.sebastian.domain.NoNetworkException
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.BaseViewModel
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import com.nikolaykul.sebastian.utils.rx.RxRelay
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : BaseViewModel() {

    private val loadingRelay = RxRelay<Boolean>()
    private val feedsRelay = RxRelay<List<RssFeed>>()

    fun observeLoading() = loadingRelay.observe()

    fun observeFeeds() = feedsRelay.observe()

    fun loadChannel() {
        launch(UI) {
            loadingRelay.push(true)
            try {
                val channel = getChannelUseCase.execute()
                feedsRelay.push(channel.feeds)
            } catch (e: NoNetworkException) {
                Timber.d(e, "Loading channel error")
            }
            loadingRelay.push(false)
        }.attachToLifecycle()
    }

}
