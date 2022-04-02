package com.example.promly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.promly.TwentyByTwenty.TwentybyTwentyHomeActivity
import com.unsplash.pickerandroid.photopicker.Injector
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerViewModel


class NewGoalActivity : AppCompatActivity() {

    private lateinit var goal_title : EditText
    private lateinit var mViewModel: UnsplashPickerViewModel
    private lateinit var save_link: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_goal)

        goal_title = findViewById(R.id.editText1)
        save_link = findViewById(R.id.save_goal)

        mViewModel = ViewModelProviders.of(this, Injector.createPickerViewModelFactory())
            .get(UnsplashPickerViewModel::class.java)
        /*This code retrieves the chosen photo from Unsplash*/
        val unsplashWindow = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->

            if(result.resultCode==Activity.RESULT_OK){
                val data : Intent? = result.data
                val photos : ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
                val intent = Intent(this, TwentybyTwentyHomeActivity::class.java)
                intent.putExtra("goal_name", goal_title.text.toString())
                intent.putExtra("goal_index",getIntent().getIntExtra("goal_index",0))
                intent.putExtra("goal_thumbnail",photos?.get(0)?.urls?.small)
                startActivity(intent)

            }
        }

        save_link.setOnClickListener {
            mViewModel.bindSearch(goal_title)
            unsplashWindow.launch(UnsplashPickerActivity.getStartingIntent(this,isMultipleSelection = false))
        }

        if(getSupportActionBar()!=null) {
            getSupportActionBar()?.hide()
        }
    }


}