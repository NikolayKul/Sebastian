package io.seekord.sebastian.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatActivity
import io.seekord.sebastian.di.Injectable
import io.seekord.sebastian.presentation.BaseNavigator
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */

abstract class BaseActivity<B : ViewDataBinding>
    : MvpAppCompatActivity(), Injectable, ErrorMvpView {

    private val navigator: Navigator by lazy { createActivityNavigator() }

    @Inject lateinit var navigatorHolder: NavigatorHolder
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

    /**
     * Provide [ru.terrakok.cicerone.Navigator]. The default is [BaseNavigator]
     */
    protected open fun createActivityNavigator() = BaseNavigator(this)

    @LayoutRes
    protected abstract fun getLayoutId(): Int

}