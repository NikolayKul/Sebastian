package com.nikolaykul.sebastian.presentation.base

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Job

abstract class BaseViewModel : ViewModel() {
    private val jobs: MutableSet<Job> = mutableSetOf()

    override fun onCleared() {
        clearJobs()
    }

    protected fun Job.attachToLifecycle() {
        jobs += this
        invokeOnCompletion {
            jobs.remove(this)
        }
    }

    private fun clearJobs() {
        with(jobs) {
            forEach { it.cancel() }
            clear()
        }
    }

}