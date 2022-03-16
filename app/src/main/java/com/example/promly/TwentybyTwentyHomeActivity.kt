package com.example.promly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class TwentybyTwentyHomeActivity : AppCompatActivity() {

    private lateinit var goal1 : ImageButton
    private lateinit var goal2: ImageButton
    private lateinit var goal3 : ImageButton
    private lateinit var goal4 : ImageButton
    private lateinit var goal5 : ImageButton
    private lateinit var goal6 : ImageButton
    private lateinit var goal7 : ImageButton
    private lateinit var goal8 : ImageButton
    private lateinit var goal9 : ImageButton
    private lateinit var goal10 : ImageButton
    private lateinit var goal11 : ImageButton
    private lateinit var goal12 : ImageButton
    private lateinit var goal13 : ImageButton
    private lateinit var goal14 : ImageButton
    private lateinit var goal15 : ImageButton
    private lateinit var goal16 : ImageButton
    private lateinit var goal17 : ImageButton
    private lateinit var goal18 : ImageButton
    private lateinit var goal19 : ImageButton
    private lateinit var goal20 : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twenty_by_twenty_home)

        var goals = arrayOfNulls<View>(20)
                        goals[1] = findViewById(R.id.empty2)
                        goals[2] = findViewById(R.id.empty3)
                        goals[3] = findViewById(R.id.empty4)
                        goals[4] = findViewById(R.id.empty5)
                        goals[5] = findViewById(R.id.empty6)
                        goals[6] = findViewById(R.id.empty7)
                        goals[7] = findViewById(R.id.empty8)
                        goals[8] = findViewById(R.id.empty9)
                        goals[9] = findViewById(R.id.empty10)
                        goals[10] = findViewById(R.id.empty11)
                        goals[11] = findViewById(R.id.empty12)
                        goals[12] = findViewById(R.id.empty13)
                        goals[13] = findViewById(R.id.empty14)
                        goals[14] = findViewById(R.id.empty15)
                        goals[15] = findViewById(R.id.empty16)
                        goals[16] = findViewById(R.id.empty17)
                        goals[17] = findViewById(R.id.empty18)
                        goals[18] = findViewById(R.id.empty19)
                        goals[19] = findViewById(R.id.empty20)

                    for(goal in goals) {
                        goal?.setOnClickListener {
                            val intent = Intent(this, NewGoalActivity::class.java)
                startActivity(intent);
            }
        }
    }
}