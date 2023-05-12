package com.example.musicapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.musicapp.R
import com.example.musicapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.bottomNavigation
        navController = findNavController(
            R.id.fragmentContainerView
        )
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.genreFragment,
                R.id.artistFragment,
                R.id.artistDetailFragment,
                R.id.trackFragment,
                R.id.favoritesFragment
            ))

        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }
}