package com.nikolaykul.sebastian.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.Job

/**
 * @author NikolayKul
 */

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val jobs: MutableSet<Job> = mutableSetOf()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
        clearJobs()
    }

    protected fun Disposable.attachToLifecycle() = disposables.add(this)

    protected fun Job.attachToLifecycle() {
        jobs += this
        invokeOnCompletion { jobs.remove(this) }
    }

    private fun clearJobs() {
        jobs.filter { it.isActive }.forEach { it.cancel() }
        jobs.clear()
    }

}