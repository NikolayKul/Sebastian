package com.nikolaykul.sebastian.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolaykul.sebastian.di.Injectable
import com.nikolaykul.sebastian.utils.vm.viewModelDelegate
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<TBinding : ViewDataBinding> : Fragment(), Injectable {

    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: TBinding
    private val disposables = CompositeDisposable()

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    protected inline fun <reified T : ViewModel> lazyViewModelDelegate() =
        viewModelDelegate<T>(activity!!, { viewModelFactory })

    protected fun <T> Flowable<T>.easySubscribe(consumer: (T) -> Unit) {
        observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer::invoke, Timber::e)
            .also { disposables.add(it) }
    }

}
