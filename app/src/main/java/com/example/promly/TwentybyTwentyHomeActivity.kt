package com.example.promly.TwentyByTwenty

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.promly.R
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


class TwentybyTwentyHomeActivity : AppCompatActivity() {


    /* These variables access the recycler view and goal adapter */
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: StaggeredGridLayoutManager
    private lateinit var adapter: GoalAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_twenty_by_twenty_home)

        supportActionBar?.title = "My 20by20"                            //this is temporary till we fix the actionbars

        /* initializes arraylist of goals (card view) */
        val goalList = ArrayList<Goal>()
        //Placeholder code for Firebase below
        for(i in 0..19){
            goalList.add(Goal())
        }
        var goal_index = intent.getIntExtra("goal_index",0)
        goalList.get(goal_index).goalTitle = intent.getStringExtra("goal_name")
        goalList.get(goal_index).goalImage = intent.getStringExtra("goal_thumbnail")
        goalList.get(goal_index).date_created = Date()


        /* initializes recyclerview and sets up the layout manager of the recycler view*/
        recyclerView = findViewById(R.id.twenty_recycler_view)

        gridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        recyclerView.layoutManager = gridLayoutManager

        adapter = GoalAdapter(goalList)

        recyclerView.adapter = adapter


    }

}