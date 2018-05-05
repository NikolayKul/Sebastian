package io.seekord.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityMainBinding
import io.seekord.sebastian.domain.rss.models.RssFeed
import io.seekord.sebastian.presentation.base.BaseActivity
import io.seekord.sebastian.presentation.main.adapter.MainFeedAdapter
import javax.inject.Inject

/**
 * @author NikolayKul
 */

class MainActivity : BaseActivity<ActivityMainBinding>(), MainMvpView {
    companion object {
        fun createStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject @InjectPresenter lateinit var presenter: MainPresenter
    private val adapter = MainFeedAdapter()

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initListeners()
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun showFeeds(feeds: List<RssFeed>) {
        adapter.setItems(feeds)
    }

    private fun initListeners() {
        binding.fab.setOnClickListener { presenter.loadFeeds() }
    }

    private fun initRecyclerView() {
        binding.rvFeeds.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

}