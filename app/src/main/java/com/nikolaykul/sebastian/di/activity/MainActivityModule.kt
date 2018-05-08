package com.nikolaykul.sebastian.di.activity

import com.nikolaykul.sebastian.presentation.BaseNavigator
import com.nikolaykul.sebastian.presentation.main.MainActivity
import com.nikolaykul.sebastian.presentation.main.MainPresenter
import com.nikolaykul.sebastian.presentation.main.MainPresenterImpl
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

    @Provides
    fun presenter(presenter: MainPresenterImpl): MainPresenter = presenter

}
