package com.example.promly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val intent = Intent(this, CodeActivity::class.java)
        intent.putExtra("key", "Code")
        startActivity(intent)

//        val mainButton = findViewById<Button>(R.id.mainButton)
//
//
//        mainButton?.setOnClickListener()
//        {
//            val intent = Intent(this, CodeActivity::class.java)
//            intent.putExtra("key", "Code")
//            startActivity(intent)
//             }
    }
    }
