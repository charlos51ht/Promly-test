package com.example.promly

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity(){

    //declare widgets that will be accessed in code
    private lateinit var emailButton : Button
    private lateinit var appleButton : Button
    private lateinit var googleButton : Button

    /**
     * This is the equivalent to the "main" function in other languages
     * Note: Do not write code above "setContentView", it will crash the code
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //this connects the layout -> code, allows access to widgets
        setContentView(R.layout.activity_intro)

        //this line sets the action bar background to the "navbar_gradient" drawable
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_navbar, null))

        //initialize widgets
        emailButton = findViewById(R.id.intro_email_button)
        appleButton = findViewById(R.id.intro_apple_button)
        googleButton = findViewById(R.id.intro_google_button)
    }


}