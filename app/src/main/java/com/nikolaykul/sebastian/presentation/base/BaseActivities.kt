package com.nikolaykul.sebastian.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.presentation.BaseNavigator
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

abstract class BaseActivity<TBinding : ViewDataBinding> : RouterActivity() {
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: TBinding
    private val disposables = CompositeDisposable()

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    protected inline fun <reified T : ViewModel> viewModelDelegate() =
        viewModelActivityDelegate<T>(this, { viewModelFactory })

    protected fun <T> Flowable<T>.easySubscribe(consumer: (T) -> Unit) {
        observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer::invoke, Timber::e)
            .also { disposables.add(it) }
    }
}


abstract class RouterActivity : InjectableActivity() {
    @Inject protected lateinit var navigatorHolder: NavigatorHolder
    private val navigator: Navigator by lazy(::provideNavigator)

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    protected open fun provideNavigator(): Navigator = BaseNavigator(this)
}


abstract class InjectableActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    final override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
