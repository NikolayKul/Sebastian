package io.seekord.sebastian.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.domain.rss.LoadRssPreviewsUseCase
import io.seekord.sebastian.presentation.base.BasePresenter
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.IO
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.UI
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@InjectViewState
class MainPresenter @Inject constructor(
        private val previewsUseCase: LoadRssPreviewsUseCase
) : BasePresenter<MainMvpView>() {
    private var job: Job? = null

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    fun loadRssPreviews() {
        job = launch(IO) {
            val previews = previewsUseCase.loadRssPreviews()
            launch(UI) { viewState.showRssPreviews(previews) }
        }
    }

}