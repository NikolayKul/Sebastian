package io.seekord.sebastian.di.activity

import dagger.Module
import dagger.Provides
import io.seekord.sebastian.presentation.BaseNavigator
import io.seekord.sebastian.presentation.main.MainActivity
import io.seekord.sebastian.presentation.main.MainPresenter
import io.seekord.sebastian.presentation.main.MainPresenterImpl
import ru.terrakok.cicerone.Navigator

/**
 * @author NikolayKul
 */

@Module
class MainActivityModule {

    @Provides
    fun navigator(activity: MainActivity): Navigator = BaseNavigator(activity)

    @Provides
    fun presenter(presenter: MainPresenterImpl): MainPresenter = presenter

}
