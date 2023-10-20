package com.example.learnandroid

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.example.learnandroid.databinding.ActivityLoginBinding
import com.example.learnandroid.presentation.screens.login.LoginFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.blue_dark)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
                val loginFragment = navHostFragment?.childFragmentManager?.fragments?.first() as LoginFragment?
                loginFragment?.let {
                    if (!it.isBackPressEnabled()) {
                        finish()
                    }
                } ?: run {
                    finish()
                }
            }
        })
    }
}