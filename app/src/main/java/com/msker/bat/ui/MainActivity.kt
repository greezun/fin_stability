package com.msker.bat.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.msker.bat.R.id
import com.msker.bat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val f by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(f.root)
    }

    override fun onResume() {
        super.onResume()
        val navController = f.navHost.findNavController()
        f.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {
                id.item_1 -> {
                    navController.navigate(id.balance)
                }

                id.item_2 -> {
                    navController.navigate(id.calk)
                }

                id.item_3 -> {
                    navController.navigate(id.accumulation)
                }
            }
            true

        }

        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {
                ActivityCompat.finishAffinity(this@MainActivity)
            }

        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

}