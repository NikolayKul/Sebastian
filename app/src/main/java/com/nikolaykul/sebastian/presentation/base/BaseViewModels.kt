package com.nikolaykul.sebastian.presentation.base

import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import com.nikolaykul.sebastian.utils.common.CoroutineDispatchersProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

/**
 *  A base ViewModel class that holds a [ViewState]
 */
abstract class StatefulViewModel<TState : ViewState> : StatelessViewModel() {
    private val stateRelay by lazy { BehaviorSubject.createDefault(initState) }

    protected abstract val initState: TState

    fun observeState(): Flowable<TState> = stateRelay.toFlowable(BackpressureStrategy.LATEST)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun nextState(reducer: (TState) -> TState) {
        stateRelay.value
            .let(reducer)
            .also(stateRelay::onNext)
    }
}


abstract class StatelessViewModel : ViewModel(), CoroutineScope {
    private val lifecycleJob: Job = Job()

    override val coroutineContext = lifecycleJob + CoroutineDispatchersProvider.MAIN

    override fun onCleared() {
        lifecycleJob.cancel()
    }
}
