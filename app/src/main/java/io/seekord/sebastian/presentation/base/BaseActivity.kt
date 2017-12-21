package io.seekord.sebastian.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.di.DependencyManager
import timber.log.Timber

/**
 * Created by Nikolay Kulachenko
 */
abstract class BaseActivity<B : ViewDataBinding> : MvpAppCompatActivity(), ErrorMvpView {
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        injectSelf(DependencyManager.createActivityComponent(this))
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun showError(error: Throwable) {
        Timber.e(error)
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun providePresenter(): BasePresenter<out MvpView>

    protected abstract fun injectSelf(component: ActivityComponent)

}