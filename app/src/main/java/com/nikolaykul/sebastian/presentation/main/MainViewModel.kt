package com.nikolaykul.sebastian.presentation.main

import android.arch.lifecycle.ViewModel
import com.nikolaykul.sebastian.domain.rss.GetChannelUseCase
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider.UI
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : ViewModel() {

    private val loadingRelay = BehaviorRelay<Boolean>()
    private val feedsRelay = BehaviorRelay<List<RssFeed>>()

    fun observeLoading() = loadingRelay.observe()

    fun observeFeeds() = feedsRelay.observe()

    fun loadChannel() {
        launch(UI) {
            loadingRelay.push(true)
            val channel = getChannelUseCase.execute()
            loadingRelay.push(false)
            feedsRelay.push(channel.feeds)
        }
    }

}

// TODO: move to utils
private class BehaviorRelay<T>(
        private val _relay: BehaviorSubject<T> = BehaviorSubject.create()
) {

    fun observe(strategy: BackpressureStrategy = BackpressureStrategy.LATEST): Flowable<T> =
            _relay.toFlowable(strategy)

    fun push(value: T) {
        _relay.onNext(value)
    }

}
