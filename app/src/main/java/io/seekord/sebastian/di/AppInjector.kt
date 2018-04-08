package io.seekord.sebastian.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection
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
        app.registerOnActivityCreateCallback { activity, _ -> injectActivity(activity) }
    }

    private fun injectActivity(activity: Activity) {
        if (activity is Injectable) {
            AndroidInjection.inject(activity)
        }
    }

    private fun injectApp(app: App) {
        DaggerAppComponent.builder()
                .context(app)
                .build()
                .inject(app)
    }

}


/**
 * Helper method that uses [Application.registerActivityLifecycleCallbacks]
 * to invoke [block] on *onActivityCreated*
 *
 * @author NikolayKul
 */
private fun Application.registerOnActivityCreateCallback(block: (Activity, Bundle?) -> Unit) {
    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            block(activity, savedInstanceState)
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
