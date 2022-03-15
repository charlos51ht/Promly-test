package com.example.promly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class twenty_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twenty_home)
        if(getSupportActionBar()!=null) {
            getSupportActionBar()?.hide()
        }

    }
}