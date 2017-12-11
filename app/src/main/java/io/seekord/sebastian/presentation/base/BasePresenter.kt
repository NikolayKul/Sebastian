package io.seekord.sebastian.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by nikolay
 */

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    protected fun Disposable.attachToLifecycle() = disposables.add(this)

}