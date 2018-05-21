package com.nikolaykul.sebastian.di.activity

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.nikolaykul.sebastian.di.application.ViewModelKey
import com.nikolaykul.sebastian.domain.rss.FeedId
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsActivity
import com.nikolaykul.sebastian.presentation.feed.details.FeedDetailsViewModel
import com.nikolaykul.sebastian.presentation.feed.details.feedId
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        CommonActivityModule::class,
        FeedDetailsActivityModule.Declarations::class
    ]
)
object FeedDetailsActivityModule {

    @JvmStatic
    @Provides
    fun feedId(activity: FeedDetailsActivity): FeedId = activity.feedId

    @Module
    interface Declarations {
        @Binds
        fun appCompat(activity: FeedDetailsActivity): AppCompatActivity

        @Binds
        @IntoMap
        @ViewModelKey(FeedDetailsViewModel::class)
        fun feedDetailsViewModel(viewModel: FeedDetailsViewModel): ViewModel
    }
}
