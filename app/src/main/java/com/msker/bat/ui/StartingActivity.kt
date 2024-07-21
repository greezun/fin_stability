package com.msker.bat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.msker.bat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}