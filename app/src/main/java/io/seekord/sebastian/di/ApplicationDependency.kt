package io.seekord.sebastian.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import io.seekord.sebastian.data.api.SebastianApiFactory
import io.seekord.sebastian.data.service.auth.SebastianAuthenticatorService
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by nikolay
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun plusActivityComponent(module: ActivityModule): ActivityComponent
    fun inject(service: SebastianAuthenticatorService)
}


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @Singleton
    @AppContext
    fun provideApplicationContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApi(apiFactory: SebastianApiFactory) = apiFactory.createStubApiWithDelay()

}

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class AppContext