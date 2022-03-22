package com.example.promly.TwentyByTwenty

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.promly.R
import com.example.promly.TwentyByTwenty.SpannedGridLayoutManager.GridSpanLookup
import com.example.promly.TwentyByTwenty.SpannedGridLayoutManager.SpanInfo


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

            for(i in 0..19){
                goalList.add(Goal())
            }

            /* initializes recyclerview and sets up the layout manager of the recycler view*/
            recyclerView = findViewById(R.id.twenty_recycler_view)

            gridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

            recyclerView.layoutManager = gridLayoutManager

            adapter = GoalAdapter(goalList)

            recyclerView.adapter = adapter


            }

}
