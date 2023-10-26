package com.example.onenex_code_test_news_app.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.onenex_code_test_news_app.R
import com.example.onenex_code_test_news_app.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = this.findNavController(R.id.nav_host_news_fragment_container)

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}