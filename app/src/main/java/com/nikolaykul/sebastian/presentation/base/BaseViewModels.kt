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
abstract class StatefulViewModel<TState : ViewState> : BaseViewModel() {
    private val stateRelay by lazy { relayInitializer() }
    protected val currentState: TState? get() = stateRelay.value

    protected open val defaultState: TState? = null

    fun observeState(): Flowable<TState> = stateRelay.toFlowable(BackpressureStrategy.LATEST)

    protected fun mutateState(mutator: (TState) -> TState) {
        val oldState = currentState ?: throw IllegalStateException("Can't mutate an empty state!")
        oldState
            .let(mutator)
            .also(this::newState)
    }

    protected fun newState(newState: TState) {
        stateRelay.onNext(newState)
    }

    private fun relayInitializer(): BehaviorSubject<TState> = if (defaultState != null) {
        BehaviorSubject.createDefault(defaultState)
    } else {
        BehaviorSubject.create()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun setTestState(testState: TState) {
        newState(testState)
    }

}


abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val lifecycleJob: Job = Job()

    override val coroutineContext = lifecycleJob + CoroutineDispatchersProvider.MAIN

    override fun onCleared() {
        lifecycleJob.cancel()
    }
}
