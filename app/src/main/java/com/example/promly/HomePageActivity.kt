package com.example.promly

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.promly.TwentyByTwenty.Goal
import java.util.ArrayList

class HomePageActivity : AppCompatActivity() {

    private lateinit var twenty_by : ImageButton
    private lateinit var one_by_two : ImageButton
    private lateinit var expand_circle : ImageButton
    private lateinit var we_got_you : ImageButton
    private lateinit var spin_me : ImageButton
    private lateinit var shop : ImageButton
    private lateinit var hustle : ImageButton
    private lateinit var share_your_voice : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        twenty_by = findViewById(R.id.twentybytwenty)
        expand_circle = findViewById(R.id.ExpandYourCircle)
        var user_id = this.intent.getStringExtra("user-id")
        twenty_by.setOnClickListener {
            val intent = Intent(this, TwentybyTwentyHomeActivity::class.java)
            if(firsttime) {
                intent.putExtra("first_time", true)
                firsttime=false
            }
            //intent.putExtra("user_id",user_id)
            startActivity(intent);
        }
        expand_circle.setOnClickListener {
            val intent = Intent(this, FindFriendsActivity::class.java)
            intent.putExtra("user-id","user-1")
            startActivity(intent);
        }
    }
    companion object{
        var firsttime = true
    }
}