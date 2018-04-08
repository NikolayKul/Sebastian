package io.seekord.sebastian.di.activity

import dagger.Module
import dagger.Provides
import io.seekord.sebastian.presentation.BaseNavigator
import io.seekord.sebastian.presentation.main.MainActivity
import ru.terrakok.cicerone.Navigator

/**
 * @author NikolayKul
 */

@Module
class MainActivityModule {

    @Provides
    @PerActivity
    fun navigator(activity: MainActivity): Navigator = BaseNavigator(activity)

}
