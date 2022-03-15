package com.example.promly

import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.promly.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {

    private lateinit var twenty_by : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        twenty_by = findViewById(R.id.twenty_by)
        addCodeTheme()
        twenty_by.setOnClickListener{

        }
    }
    private fun addCodeTheme()
    {

    }
}