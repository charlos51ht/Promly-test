package com.example.promly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton


class TwentybyTwentyHomeActivity : AppCompatActivity() {

    private lateinit var goal1 : Button
    private lateinit var goal2: Button
    private lateinit var goal3 : Button
    private lateinit var goal4 : Button
    private lateinit var goal5 : Button
    private lateinit var goal6 : Button
    private lateinit var goal7 : Button
    private lateinit var goal8 : Button
    private lateinit var goal9 : Button
    private lateinit var goal10 : Button
    private lateinit var goal11 : Button
    private lateinit var goal12 : Button
    private lateinit var goal13 : Button
    private lateinit var goal14 : Button
    private lateinit var goal15 : Button
    private lateinit var goal16 : Button
    private lateinit var goal17 : Button
    private lateinit var goal18 : Button
    private lateinit var goal19 : Button
    private lateinit var goal20 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twenty_by_twenty_home)


        var goals = arrayOfNulls<Button>(20)
        goals[0] = findViewById(R.id.empty1)
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

        var goal_index = getIntent().getIntExtra("goal_index",0)
        var goal_text = getIntent().getStringExtra("goal_name")
        goals[goal_index]?.setText(goal_text.toString())
        for(i in 0..19) {
            var params = goals[i]?.layoutParams
            if(params?.width!=params?.height)
                goals[i]?.setBackgroundResource(R.drawable.empty_long)
            else
                goals[i]?.setBackgroundResource(R.drawable.empty_goal)
            goals[i]?.setOnClickListener {
                    val intent = Intent(this, NewGoalActivity::class.java)
                    intent.putExtra("goal_index",i)
                startActivity(intent);
            }
        }
    }
}