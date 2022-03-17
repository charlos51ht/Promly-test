package com.example.promly

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


private lateinit var goal_title : EditText
private lateinit var submit: Button

class NewGoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_goal)
        goal_title = findViewById(R.id.editText1)
        submit = findViewById(R.id.submit)

        submit.setOnClickListener {
            val intent = Intent(this, TwentybyTwentyHomeActivity::class.java)
            intent.putExtra("goal_name", goal_title.getText().toString())
            intent.putExtra("goal_index",getIntent().getIntExtra("goal_index",0))
            startActivity(intent);
        }

        if(getSupportActionBar()!=null) {
            getSupportActionBar()?.hide()
        }

        /*var photo_picker = UnsplashPhotoPicker.init(
            this, // application
            "ozn6JHWcOkRQZJBTfIAMh0hNON4BIn7dG8HgXc-DouE",
            "fDnCs3skmSjLQHnUOH-6dtIVRd3cHfR_SN6tEq6v07M"
        )*/
    }
}