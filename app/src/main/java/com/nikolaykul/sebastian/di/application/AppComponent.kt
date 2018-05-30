package com.nikolaykul.sebastian.di.application

import android.app.Application
import com.nikolaykul.sebastian.App
import com.nikolaykul.sebastian.di.activity.ActivityInjectionModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityInjectionModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RouterModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
