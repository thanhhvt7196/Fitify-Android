package com.example.learnandroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.learnandroid.utils.constants.AppConstants

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences =
            getSharedPreferences(AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

//        val isLoggedIn = sharedPreferences.getBoolean(AppConstants.isLoggedInSPKey, false)
//        val intent = if (isLoggedIn) {
//            Intent(this, MainActivity::class.java)
//        } else {
//            Intent(this, LoginActivity::class.java)
//        }
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        sharedPreferences.edit {
            this.putBoolean(AppConstants.IS_LOGGED_IN_SP_KEY, true)
        }
        finish()
    }
}