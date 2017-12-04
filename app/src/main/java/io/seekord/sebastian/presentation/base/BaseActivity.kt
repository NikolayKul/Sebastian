package io.seekord.sebastian.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.di.DependencyManager

/**
 * Created by Nikolay Kulachenko
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectSelf(DependencyManager.createActivityComponent(this))
    }

    protected abstract fun injectSelf(component: ActivityComponent)

}