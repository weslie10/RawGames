package com.weslie10.rawgames.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.weslie10.rawgames.MainActivity
import com.weslie10.rawgames.R
import com.weslie10.rawgames.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            FULL_SCREEN_FLAG,
            FULL_SCREEN_FLAG
        )
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME)
    }

    companion object {
        private const val TIME = 3000L
        private const val FULL_SCREEN_FLAG = 1024
    }
}