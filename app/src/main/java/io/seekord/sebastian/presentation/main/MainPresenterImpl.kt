package io.seekord.sebastian.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.domain.rss.GetChannelUseCase
import io.seekord.sebastian.utils.common.CoroutineContextProvider.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@InjectViewState
class MainPresenterImpl @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : MainPresenter() {

    override fun loadFeeds() {
        launch(UI) {
            val channel = try {
                getChannelUseCase.execute()
            } catch (e: Exception) {
                viewState.showError(e)
                return@launch
            }

            viewState.showFeeds(channel.feeds)

        }.attachToLifecycle()
    }

}