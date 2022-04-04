package com.example.promly

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.promly.TwentybyTwentyHomeActivity
import com.unsplash.pickerandroid.photopicker.Injector
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerViewModel
import kotlin.random.Random


class NewGoalActivity : AppCompatActivity() {

    private lateinit var goal_title : EditText
    private lateinit var mViewModel: UnsplashPickerViewModel
    private lateinit var save_link: TextView
    private lateinit var back_20: ImageButton
    private lateinit var view : View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_goal)


        //Randomizes new goal background based on 8 different color schemes
        val back_images = intArrayOf(R.drawable.gradient_new_goal_background1, R.drawable.gradient_new_goal_background2,
            R.drawable.gradient_new_goal_background3, R.drawable.gradient_new_goal_background4,
            R.drawable.gradient_new_goal_background5,R.drawable.gradient_new_goal_background6,
            R.drawable.gradient_new_goal_background7, R.drawable.gradient_new_goal_background8)
        val rand = (0..7).shuffled().last()
        view = findViewById(R.id.new_goal_layout)
        view.setBackgroundResource(back_images[rand])




        goal_title = findViewById(R.id.editText1)
        save_link = findViewById(R.id.save_goal)
        back_20 = findViewById(R.id.back_20)

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
        back_20.setOnClickListener{
            var homeIntent = Intent(this, TwentybyTwentyHomeActivity::class.java)
            startActivity(homeIntent)
        }
    }


}