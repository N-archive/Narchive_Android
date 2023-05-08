package com.chunbae.narchive.presentation.ui.signin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.databinding.ActivitySignInBinding
import com.chunbae.narchive.presentation.ui.main.MainActivity
import com.chunbae.narchive.presentation.ui.signin.viewmodel.SignInViewModel
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    private val viewModel : SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        initBinding()
        kakaoIdObserve()
    }

    override fun onStart() {
        super.onStart()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun initBinding() {
        binding.activity = this
    }

    fun checkKakaoTalkAvailable() {
        if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            signInWithKakao()
        } else {
            signInWithKakaoAccount()
        }
    }

    private fun signInWithKakao() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                signInWithKakaoAccount()
            }
            else if (token != null) {
                getUserId()
            }
        }
    }

    private fun signInWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            showError()
        } else if (token != null) {
            getUserId()
        } else {
        }
    }

    private fun kakaoIdObserve() {
        viewModel.userKakaoId.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun getUserId() {
        UserApiClient.instance.me { user, error ->
            if(error == null && user != null) {
                viewModel.setUserKakaoId(user.id.toString())
            } else {
                Log.d("----", "getUserId: ${error?.message}")
            }
        }
    }

    private fun showError() {
        Snackbar.make(binding.root, "알 수 없는 오류가 발생했습니다.\n잠시 후 다시 시도해주세요.", Snackbar.LENGTH_SHORT).show()
    }
}