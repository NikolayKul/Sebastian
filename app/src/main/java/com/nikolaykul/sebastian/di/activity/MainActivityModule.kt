package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.main.MainActivity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Navigator

/**
 * @author NikolayKul
 */

@Module
class MainActivityModule {

    @Provides
    fun navigator(activity: MainActivity): Navigator = BaseNavigator(activity)

}
