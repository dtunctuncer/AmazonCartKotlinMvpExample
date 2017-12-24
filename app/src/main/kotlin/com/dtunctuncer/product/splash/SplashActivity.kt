package com.dtunctuncer.product.splash

import com.dtunctuncer.product.main.MainActivity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dtunctuncer.product.base.BaseActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}