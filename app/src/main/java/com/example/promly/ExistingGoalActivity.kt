package com.example.promly

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class ExistingGoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_existing_goal)

        var goal_title : TextView = findViewById(R.id.goal_title_big)
        var image: ImageView = findViewById(R.id.big_goal_img)
        goal_title.text = intent.getStringExtra("title").toString()
        var date_created : TextView = findViewById(R.id.goal_date)
        date_created.text = "Date Created: "+ intent.getStringExtra("date_created").toString()
        Picasso.get().load(intent.getStringExtra("image_url").toString()).fit().into(image)
        //unsplash API
    }
}