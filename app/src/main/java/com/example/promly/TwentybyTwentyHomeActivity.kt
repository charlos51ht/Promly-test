package com.example.promly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promly.TwentyByTwenty.Goal
import com.example.promly.TwentyByTwenty.GoalAdapter
import java.util.*


class TwentybyTwentyHomeActivity : AppCompatActivity() {


    /* These variables access the recycler view and goal adapter */
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: SpannedGridLayoutManager
    private lateinit var adapter: GoalAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_twenty_by_twenty_home)
        toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        /* initializes arraylist of goals (card view) */


        //Placeholder code for Firebase below
        //remove if statement on firebase addition
        if(intent.getStringExtra("from_home")!=null) {
            for (i in 0..19) {
                goalList.add(Goal())
            }
        }
        val goalIndex = intent.getIntExtra("goal_index",0)
        goalList.get(goalIndex).goalTitle = intent.getStringExtra("goal_name")
        goalList.get(goalIndex).goalImage = intent.getStringExtra("goal_thumbnail")
        goalList.get(goalIndex).date_created = Date()
        goalList.get(goalIndex).goalStatus = intent.getIntExtra("goal_status", 0)
        goalList.get(goalIndex).details = intent.getStringExtra("goal_details")

        /* initializes recyclerview and sets up the layout manager of the recycler view*/
        recyclerView = findViewById(R.id.twenty_recycler_view)

        gridLayoutManager = SpannedGridLayoutManager(orientation = LinearLayoutManager.VERTICAL, spans = 3)
        gridLayoutManager.itemOrderIsStable = true

        recyclerView.layoutManager = gridLayoutManager

        adapter = GoalAdapter(goalList)

        gridLayoutManager.spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
            if (position == 0 || position == 6 || position == 10 || position == 16) {
                SpanSize(2, 2)
            }else if(position == 4 || position == 8 || position == 14 || position == 18) {
                SpanSize(2, 1)
            }else{
                SpanSize(1, 1)
            }
        }

        recyclerView.adapter = adapter

    }
    companion object{
        val goalList = ArrayList<Goal>()
    }



}