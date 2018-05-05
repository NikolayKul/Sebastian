package io.seekord.sebastian.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.domain.channel.GetChannelUseCase
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@InjectViewState
class MainPresenterImpl @Inject constructor(
        private val getChannelUseCase: GetChannelUseCase
) : MainPresenter() {

    override fun loadRssPreviews() {
        launch(UI) {
            val channel = try {
                getChannelUseCase.execute()
            } catch (e: Exception) {
                viewState.showError(e)
                return@launch
            }

            viewState.showRssPreviews(channel.feeds)

        }.attachToLifecycle()
    }

}