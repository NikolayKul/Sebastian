package com.nikolaykul.sebastian.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityMainBinding
import com.nikolaykul.sebastian.domain.rss.models.RssFeed
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import com.nikolaykul.sebastian.presentation.main.adapter.MainFeedAdapter
import com.nikolaykul.sebastian.utils.common.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KProperty

/**
 * @author NikolayKul
 */

class MainActivity : BaseActivity<ActivityMainBinding>(), MainMvpView {
    companion object {
        fun createStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel //by viewModelD(this, viewModelFactory)
    private val adapter = MainFeedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initListeners()
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        initObservers()
    }

    private fun initObservers() {
        viewModel.observeFeeds()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (this::showFeeds),
                        (Timber::e))
        viewModel.observeLoading()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Timber.d("Show loading: %b", it) },
                        (Timber::e))
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun showFeeds(feeds: List<RssFeed>) {
        adapter.setItems(feeds)
    }

    private fun initListeners() {
        binding.fab.setOnClickListener { viewModel.loadChannel() }
//        binding.fab.setOnClickListener { presenter.loadFeeds() }
    }

    private fun initRecyclerView() {
        binding.rvFeeds.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

}

private inline fun <reified T : ViewModel> viewModelD(
        activity: FragmentActivity,
        viewModelFactory: ViewModelFactory): ViewModelDelegate<T> {
    return ViewModelDelegate(T::class.java, activity, viewModelFactory)
}

private class ViewModelDelegate<T : ViewModel>(
        private val clazz: Class<T>,
        private val activity: FragmentActivity,
        private val viewModelFactory: ViewModelFactory
) {

    private var viewModel: T? = null

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        return viewModel ?: getAndCache()
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: T) {
        viewModel = value
    }

    private fun getAndCache() = ViewModelProviders.of(activity, viewModelFactory)[clazz]
            .also { viewModel = it }

}