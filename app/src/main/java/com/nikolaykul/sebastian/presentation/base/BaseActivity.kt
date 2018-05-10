package com.nikolaykul.sebastian.presentation.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nikolaykul.sebastian.di.Injectable
import com.nikolaykul.sebastian.utils.vm.viewModelDelegate
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */

abstract class BaseActivity<B : ViewDataBinding>
    : MvpAppCompatActivity(), Injectable, ErrorMvpView {

    @Inject protected lateinit var navigator: Navigator
    @Inject protected lateinit var navigatorHolder: NavigatorHolder
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
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

    override fun showError(error: Throwable) {
        Timber.e(error)
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected inline fun <reified T : ViewModel> lazyViewModelDelegate() =
            viewModelDelegate<T>(this, { viewModelFactory })

}