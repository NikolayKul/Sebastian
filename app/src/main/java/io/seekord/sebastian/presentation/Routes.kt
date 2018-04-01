package io.seekord.sebastian.presentation

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import io.seekord.sebastian.presentation.main.MainActivity
import ru.terrakok.cicerone.android.SupportAppNavigator

/**
 * @author NikolayKul
 */


const val MAIN_SCREEN = "main_screen"


class BaseNavigator(
        activity: FragmentActivity,
        fragmentContainerId: Int? = null
) : SupportAppNavigator(activity, fragmentContainerId ?: 0) {

    override fun createActivityIntent(context: Context,
                                      screenKey: String,
                                      data: Any?): Intent {
        return when (screenKey) {
            MAIN_SCREEN -> MainActivity.createStartIntent(context)
            else -> throw IllegalStateException("Unknown screen key: $screenKey")
        }
    }

    override fun createFragment(screenKey: String, data: Any?): Fragment {
        TODO("not implemented")
    }

}