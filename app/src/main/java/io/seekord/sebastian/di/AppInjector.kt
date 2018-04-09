package io.seekord.sebastian.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import io.seekord.sebastian.App
import io.seekord.sebastian.di.application.DaggerAppComponent

/**
 * Class that injects Application, Activities and Fragments.
 * The last two must be marked as [Injectable]
 *
 * @author NikolayKul
 */
object AppInjector {

    fun init(app: App) {
        injectApp(app)
        app.registerOnActivityCreateCallback(this::handleActivity)
    }

    private fun handleActivity(activity: Activity) {
        if (activity is Injectable) {
            AndroidInjection.inject(activity)
        }
        (activity as? FragmentActivity)?.registerOnFragmentAttachedCallback(this::handleFragment)
    }

    private fun handleFragment(fragment: Fragment) {
        if (fragment is Injectable) {
            AndroidSupportInjection.inject(fragment)
        }
    }

    private fun injectApp(app: App) {
        DaggerAppComponent.builder()
                .application(app)
                .build()
                .inject(app)
    }

}


/**
 * Helper method that uses [FragmentManager.registerFragmentLifecycleCallbacks]
 * to invoke [block] on *onFragmentAttached*
 */
private fun FragmentActivity.registerOnFragmentAttachedCallback(block: (Fragment) -> Unit) {
    supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, fragment: Fragment, context: Context) {
            block(fragment)
        }
    }, true)
}


/**
 * Helper method that uses [Application.registerActivityLifecycleCallbacks]
 * to invoke [block] on *onActivityCreated*
 *
 * @author NikolayKul
 */
private fun Application.registerOnActivityCreateCallback(block: (Activity) -> Unit) {
    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            block(activity)
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }
    })
}
