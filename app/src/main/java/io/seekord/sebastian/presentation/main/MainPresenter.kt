package io.seekord.sebastian.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.domain.rss.GetRssItemsUseCase
import io.seekord.sebastian.presentation.base.BasePresenter
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.IO
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.UI
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@InjectViewState
class MainPresenter @Inject constructor(
        private val itemsUseCase: GetRssItemsUseCase
) : BasePresenter<MainMvpView>() {
    private var job: Job? = null

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    fun loadRssPreviews() {
        job = launch(IO) {
            val items = try {
                itemsUseCase.getPssItems()
            } catch (e: Exception) {
                Timber.e(e)
                return@launch
            }

            Timber.d("Items: $items")
            launch(UI) { viewState.showRssPreviews(items) }
        }
    }

}