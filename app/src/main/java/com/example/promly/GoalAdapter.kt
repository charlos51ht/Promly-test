package com.example.promly.TwentyByTwenty

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.promly.ExistingGoalActivity
import com.example.promly.NewGoalActivity
import com.example.promly.R
import com.squareup.picasso.Picasso


class GoalAdapter(val goals: ArrayList<Goal>):
    RecyclerView.Adapter<GoalAdapter.GoalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalHolder {
        val itemView: CardView =
            LayoutInflater.from(parent.context).inflate(R.layout.goal, parent, false) as CardView
        return GoalHolder(itemView)
    }


    override fun getItemCount() = goals.size

    override fun onBindViewHolder(holder: GoalHolder, position: Int) {

            holder.goalTitle.text = goals[position].goalTitle
            if(goals[position].goalTitle == "" || goals[position].goalTitle == null) {
                if(position == 4||position == 8||position == 14||position == 18)
                    holder.goalImage.setImageResource(R.drawable.empty_long)
                else
                    holder.goalImage.setImageResource(R.drawable.empty_goal)
            }
            else {
                Picasso.get().load(goals[position].goalImage).fit().into(holder.goalImage)
            }
            goals[position].goalStatus?.let { holder.goalStatus.setImageResource(it)
            goals[position].goalIndex = position
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
                if(goalTitle.text.toString()!="") {
                    showGoalIntent = Intent(context, ExistingGoalActivity::class.java)
                    showGoalIntent.putExtra("image_url",goals[adapterPosition].goalImage)
                    showGoalIntent.putExtra("title",goals[adapterPosition].goalTitle)
                    showGoalIntent.putExtra("date_created",goals[adapterPosition].date_created.toString())
                    showGoalIntent.putExtra("goal_status",goals[adapterPosition].goalStatus)


                }

                showGoalIntent.putExtra("goal_index", adapterPosition)
                context.startActivity(showGoalIntent)
            }
        }


    }

}