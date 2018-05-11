package com.nikolaykul.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.nikolaykul.sebastian.R
import com.nikolaykul.sebastian.databinding.ActivityMainBinding
import com.nikolaykul.sebastian.presentation.base.BaseActivity
import com.nikolaykul.sebastian.presentation.main.adapter.MainFeedAdapter
import timber.log.Timber

/**
 * @author NikolayKul
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {
    companion object {
        fun createStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel by lazyViewModelDelegate<MainViewModel>()
    private val adapter = MainFeedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initListeners()
        initObservers()
    }

    override fun getLayoutId() = R.layout.activity_main

    private fun initObservers() {
        viewModel.observeFeeds()
                .easySubscribe { adapter.setItems(it) }
        viewModel.observeLoading()
                .easySubscribe { Timber.d("Show loading: %b", it) }
    }

    private fun initListeners() {
        binding.fab.setOnClickListener { viewModel.loadChannel() }
    }

    private fun initRecyclerView() {
        binding.rvFeeds.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

}