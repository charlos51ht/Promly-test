package com.example.promly

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

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

        //setup so that regardless of what you click it takes you to the homepage for now
        //may be a cleaner way to do this
        emailButton.setOnClickListener{
                val intent = Intent(this, HomePageActivity::class.java)
                var db= FirebaseFirestore.getInstance()
                val user = db.collection("users").document("user-1")
                user.get().addOnSuccessListener{ document ->
                        intent.putExtra("user_id", "dev-test")
                }
                startActivity(intent);
        }
        appleButton.setOnClickListener{
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent);
        }
        googleButton.setOnClickListener{
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent);
        }


    }


}