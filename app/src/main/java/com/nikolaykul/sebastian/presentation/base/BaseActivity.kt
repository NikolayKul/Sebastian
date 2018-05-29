package com.nikolaykul.sebastian.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.utils.vm.viewModelActivityDelegate
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject protected lateinit var navigator: Navigator
    @Inject protected lateinit var navigatorHolder: NavigatorHolder
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    protected lateinit var binding: B
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected inline fun <reified T : ViewModel> viewModelDelegate() =
        viewModelActivityDelegate<T>(this, { viewModelFactory })

    protected fun <T> Flowable<T>.easySubscribe(consumer: (T) -> Unit) {
        observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer::invoke, Timber::e)
            .also { disposables.add(it) }
    }

}