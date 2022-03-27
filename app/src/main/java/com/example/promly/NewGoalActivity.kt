package com.example.promly

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.promly.TwentyByTwenty.TwentybyTwentyHomeActivity
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity


private lateinit var goal_title : EditText
private lateinit var submit: Button

class NewGoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_goal)
        goal_title = findViewById(R.id.editText1)
        submit = findViewById(R.id.submit)

        val unsplash_window = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
            if(result.resultCode==Activity.RESULT_OK){
                val data : Intent? = result.data
                val photos : ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
                val intent = Intent(this, TwentybyTwentyHomeActivity::class.java)
                intent.putExtra("goal_name", goal_title.text.toString())
                intent.putExtra("goal_index",getIntent().getIntExtra("goal_index",0))
                intent.putExtra("goal_thumbnail",photos?.get(0)?.urls?.small)
                startActivity(intent);

            }
        }

        submit.setOnClickListener {
            unsplash_window.launch(UnsplashPickerActivity.getStartingIntent(this,isMultipleSelection = false))
        }

        if(getSupportActionBar()!=null) {
            getSupportActionBar()?.hide()
        }
    }
}