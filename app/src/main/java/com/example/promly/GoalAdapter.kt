package com.example.promly.TwentyByTwenty

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.promly.ExistingGoalActivity
import com.example.promly.NewGoalActivity
import com.example.promly.R



class GoalAdapter(val goals: ArrayList<Goal>):
    RecyclerView.Adapter<GoalAdapter.GoalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalHolder {
        val itemView: CardView =
            LayoutInflater.from(parent.context).inflate(R.layout.goal, parent, false) as CardView
        return GoalHolder(itemView)
    }

//    var manager = SpannedGridLayoutManager(
//        object : GridSpanLookup {
//            override fun getSpanInfo(position: Int): SpanInfo {
//                // Conditions for 2x2 items
//                return if (position % 6 == 0 || position % 6 == 4) {
//                    SpanInfo(2, 2)
//                } else {
//                    SpanInfo(1, 1)
//                }
//            }
//        },
//        3,  // number of columns
//        1f // how big is default item
//    )

    override fun getItemCount() = goals.size

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0, 6, 10, 16 -> 3
            4, 8, 14, 18 -> 2
            else -> 1
        }

    }

    override fun onBindViewHolder(holder: GoalHolder, position: Int) {

            holder.goalTitle.text = goals[position].goalTitle
            holder.goalImage.setImageResource(goals[position].goalImage)
            goals[position].goalStatus?.let { holder.goalStatus.setImageResource(it)
            goals[position].goalIndex = position
        }


        when (getItemViewType(position)) {
            3 -> {
                holder.itemView.layoutParams.height = 500
                holder.itemView.layoutParams.width = 500
            }
            2 -> {
                holder.itemView.layoutParams.height = 250
                holder.itemView.layoutParams.width = 500
            }
            1 -> {
                holder.itemView.layoutParams.height = 250
                holder.itemView.layoutParams.width = 250

            }
        }

    }


    inner class GoalHolder(itemView: CardView): RecyclerView.ViewHolder(itemView) {

        var goalTitle: TextView = itemView.findViewById(R.id.goal_title)
        var goalImage: ImageView = itemView.findViewById(R.id.goal_image)
        var goalStatus: ImageView = itemView.findViewById(R.id.goal_status)


        //
        init {
            itemView.setOnClickListener {
                val context = itemView.context
                var showGoalIntent = Intent(context, NewGoalActivity::class.java)
                if(goalTitle.text.toString()!="")
                    showGoalIntent = Intent(context, ExistingGoalActivity::class.java)

                showGoalIntent.putExtra("goal_index", adapterPosition)
                context.startActivity(showGoalIntent)
                context.startActivity(showGoalIntent)
            }
        }


    }

}