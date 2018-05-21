package com.nikolaykul.sebastian.di.activity

import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.presentation.BaseNavigator
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Navigator

/**
 * A Module that contains only common Activity dependencies
 */
@Module
object CommonActivityModule {

    @JvmStatic
    @Provides
    fun baseNavigator(activity: AppCompatActivity): Navigator = BaseNavigator(activity)

    // other common dependencies

}
