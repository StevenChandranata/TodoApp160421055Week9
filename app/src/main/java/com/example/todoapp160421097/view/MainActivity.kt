package com.example.todoapp160421097.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.todoapp160421097.R
import com.example.todoapp160421097.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the activity's binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the NavHostFragment and NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController

        // Explicitly set the navigation graph
        navController.setGraph(R.navigation.navigation_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Obtain the NavHostFragment and NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate up with the navController or fall back to the default navigation behavior
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }
}
