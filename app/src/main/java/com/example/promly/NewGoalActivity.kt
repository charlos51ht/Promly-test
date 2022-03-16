package com.example.promly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NewGoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_goal)

        if(getSupportActionBar()!=null) {
            getSupportActionBar()?.hide()
        }

        //Code to initialize unsplash once the gradle build works properly
        //https://github.com/unsplash/unsplash-photopicker-android
/*        UnsplashPhotoPicker.init(
            this, // application
            "ozn6JHWcOkRQZJBTfIAMh0hNON4BIn7dG8HgXc-DouE",
            "fDnCs3skmSjLQHnUOH-6dtIVRd3cHfR_SN6tEq6v07M"
            /* optional page size */
        )
*/
    }
}