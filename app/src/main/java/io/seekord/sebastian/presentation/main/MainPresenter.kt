package io.seekord.sebastian.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.seekord.sebastian.presentation.base.BasePresenter
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.IO
import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider.UI
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author NikolayKul
 */

@InjectViewState
class MainPresenter @Inject constructor() : BasePresenter<MainMvpView>() {
    private var job: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        job = doShowCurrentAuthToken()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun doShowCurrentAuthToken() = async(IO) {
        val token = try {
            delay(1000)
            "SomeToken"
        } catch (e: Exception) {
            Timber.e(e)
            "No auth token"
        }
        launch(UI) { viewState.showCurrentAuthToken(token) }
    }

}