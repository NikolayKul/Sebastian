package io.seekord.sebastian.presentation.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.di.DependencyManager

/**
 * Created by Nikolay Kulachenko
 */
abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectSelf(DependencyManager.createActivityComponent(this))
    }

    protected abstract fun injectSelf(component: ActivityComponent)

}