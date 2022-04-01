package com.example.promly

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

lateinit var notes_enter : EditText
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
    }
}