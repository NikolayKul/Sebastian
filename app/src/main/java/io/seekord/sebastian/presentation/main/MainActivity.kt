package io.seekord.sebastian.presentation.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import io.seekord.sebastian.R
import io.seekord.sebastian.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Dummy title"
        toolbar.subtitle = "Dummy subtitle"
    }

}