package io.seekord.sebastian.di.application

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Module
class RouterModule {
    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun router(): Router = cicerone.router

}
