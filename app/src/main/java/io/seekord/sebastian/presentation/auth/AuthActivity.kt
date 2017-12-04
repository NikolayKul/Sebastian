package io.seekord.sebastian.presentation.auth

import android.databinding.DataBindingUtil
import android.os.Bundle
import io.seekord.sebastian.R
import io.seekord.sebastian.databinding.ActivityAuthBinding
import io.seekord.sebastian.presentation.base.BaseActivity

/**
 * Created by Nikolay Kulachenko
 */
class AuthActivity : BaseActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
    }

}