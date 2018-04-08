package io.seekord.sebastian.di.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.seekord.sebastian.App
import javax.inject.Singleton

/**
 * @author NikolayKul
 */

@Singleton
@Component(modules = [AppModule::class, RouterModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(app: Application): Builder

        fun build(): AppComponent
    }
}
