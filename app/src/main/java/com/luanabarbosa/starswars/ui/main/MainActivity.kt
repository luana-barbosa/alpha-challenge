package com.luanabarbosa.starswars.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.NavActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding: NavActivityBinding by lazy {
        NavActivityBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

    }
}