package com.nikolaykul.sebastian.di.application

import android.app.Application
import com.nikolaykul.sebastian.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Singleton
@Component(modules = [
    AppInjectionModule::class,
    ViewModelModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RouterModule::class
])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
