package com.example.promly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

private lateinit var goal_title : TextView
private lateinit var date_created : TextView
private lateinit var image: ImageView

class ExistingGoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing_goal)
        goal_title = findViewById(R.id.goal_title_big)
        image = findViewById(R.id.big_goal_img)
        goal_title.text = intent.getStringExtra("title").toString()
        date_created = findViewById(R.id.goal_date)
        date_created.text = "Date Created: "+intent.getStringExtra("date_created").toString()
        Picasso.get().load(intent.getStringExtra("image_url").toString()).fit().into(image)
        //unsplash API
    }
}