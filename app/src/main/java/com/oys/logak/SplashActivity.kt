package com.oys.logak


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.oys.logak.base.BaseActivity
import com.oys.logak.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_fade_in, R.anim.slide_fade_out)
        }, DURATION)
    }

    companion object {
        private const val DURATION: Long = 1000
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}