package io.github.pengdst.jetpacksubmission.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.ui.home.HomeActivity
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenResumed {
            delay(2000L)
            Intent(applicationContext, HomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}