package com.example.promly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class TwentybyTwentyHomeActivity : AppCompatActivity() {

    private lateinit var new_goal : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twenty_by_twenty_home)
        if(getSupportActionBar()!=null) {
            getSupportActionBar()?.hide()
        }


        new_goal = findViewById(R.id.new_goal)

        new_goal.setOnClickListener {
            val intent = Intent(this, NewGoalActivity::class.java)
            startActivity(intent);
        }

    }
}