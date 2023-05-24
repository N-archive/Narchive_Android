package com.chunbae.narchive.presentation.ui.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chunbae.narchive.presentation.ui.main.MainActivity
import com.chunbae.narchive.presentation.ui.signin.view.SignInActivity
import com.chunbae.narchive.presentation.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel : SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeSignIn()
    }

    private fun observeSignIn() {
        viewModel.autoSignInResult.observe(this) {
            if (it == "Y") startActivity(Intent(this, MainActivity::class.java))
            else startActivity(Intent(this, SignInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            finish()
        }
    }
}