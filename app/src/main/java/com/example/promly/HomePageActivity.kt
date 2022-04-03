package com.example.promly

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    private lateinit var twenty_by : ImageButton
    private lateinit var one_by_two : ImageButton
    private lateinit var exapand_circle : ImageButton
    private lateinit var we_got_you : ImageButton
    private lateinit var spin_me : ImageButton
    private lateinit var shop : ImageButton
    private lateinit var hustle : ImageButton
    private lateinit var share_your_voice : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        twenty_by = findViewById(R.id.twentybytwenty)

        twenty_by.setOnClickListener {
            val intent = Intent(this, TwentybyTwentyHomeActivity::class.java)
            intent.putExtra("from_home","from home")
            startActivity(intent);
        }
    }
}