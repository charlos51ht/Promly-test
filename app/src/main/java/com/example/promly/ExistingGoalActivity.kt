package com.example.promly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.promly.TwentyByTwenty.TwentybyTwentyHomeActivity
import com.squareup.picasso.Picasso

lateinit var notes_enter : EditText
lateinit var button_complete: Button
lateinit var button_delete: Button
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


        notes_enter = findViewById(R.id.details_box)
        button_complete = findViewById(R.id.completed_button)
        button_delete = findViewById(R.id.delete_button)

        notes_enter.setOnEditorActionListener{ v, keyCode, event ->

            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                || keyCode == EditorInfo.IME_ACTION_DONE) {
                val inputManager: InputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    this.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        var showGoalIntent = Intent(this, TwentybyTwentyHomeActivity::class.java)
        button_complete.setOnClickListener {
            showGoalIntent.putExtra("goal_name", goal_title.text.toString())
            showGoalIntent.putExtra("goal_index",getIntent().getIntExtra("goal_index",0))
            showGoalIntent.putExtra("goal_thumbnail", intent.getStringExtra("image_url"))
            showGoalIntent.putExtra("goal_status", R.drawable.baseline_check_circle_white_24)
            startActivity(showGoalIntent)
            //return to home page
        }
        button_delete.setOnClickListener {
            //delete from database logic
            //return to home page
            startActivity(showGoalIntent)
        }

    }
}