package io.seekord.sebastian.presentation.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.seekord.sebastian.R
import io.seekord.sebastian.presentation.main.MainActivity

/**
 * Created by nikolay
 */

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        // navigate & finish
        startActivity(MainActivity.createStartIntent(this))
        finish()
    }

}
