package io.seekord.sebastian.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityMainBinding
import io.seekord.sebastian.di.ActivityComponent
import io.seekord.sebastian.domain.rss.models.RssPreview
import io.seekord.sebastian.presentation.base.BaseActivity
import io.seekord.sebastian.presentation.main.adapter.RssPreviewAdapter
import javax.inject.Inject

/**
 * @author NikolayKul
 */

class MainActivity : BaseActivity<ActivityMainBinding>(), MainMvpView {
    companion object {
        fun createStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject @InjectPresenter lateinit var presenter: MainPresenter
    private val adapter = RssPreviewAdapter()

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun injectSelf(component: ActivityComponent) {
        component.inject(this)
    }

    override fun showRssPreviews(previews: List<RssPreview>) {
        adapter.setItems(previews)
    }

    private fun initRecyclerView() {
        binding.rvPreviews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

}