package io.seekord.sebastian.presentation.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.di.DependencyManager
import timber.log.Timber

/**
 * Created by Nikolay Kulachenko
 */
abstract class BaseActivity : MvpAppCompatActivity(), ErrorMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectSelf(DependencyManager.createActivityComponent(this))
        super.onCreate(savedInstanceState)
    }

    override fun showError(error: Throwable) {
        Timber.e(error)
    }

    protected abstract fun providePresenter(): BasePresenter<out MvpView>

    protected abstract fun injectSelf(component: ActivityComponent)

}