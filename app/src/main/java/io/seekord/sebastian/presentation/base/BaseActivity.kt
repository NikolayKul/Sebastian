package io.seekord.sebastian.presentation.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.di.DependencyManager

/**
 * Created by Nikolay Kulachenko
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectSelf(DependencyManager.createActivityComponent(this))
        super.onCreate(savedInstanceState)
    }

    protected abstract fun providePresenter(): BasePresenter<out MvpView>

    protected abstract fun injectSelf(component: ActivityComponent)

}