package com.example.promly

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ProfileAdapter(private val profiles: ArrayList<Profile>): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView: CardView =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_profile, parent, false) as CardView
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {

        Picasso.get().load(profiles[position].profile?.get("avatarURL")).fit().centerCrop().into(holder.profileImage)
        holder.profileName.text = profiles[position].profile?.get("name")
        holder.interestOne.text = profiles[position].interests?.get(0)
        holder.interestTwo.text = profiles[position].interests?.get(1)
        holder.interestThree.text = profiles[position].interests?.get(2)
        val db= FirebaseFirestore.getInstance()
    }

    override fun getItemCount() = profiles.size

    inner class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val profileImage: ImageView = itemView.findViewById(R.id.friend_image)
        val profileName: TextView = itemView.findViewById(R.id.friend_name)
        val interestOne: TextView = itemView.findViewById(R.id.interest_1)
        val interestTwo: TextView = itemView.findViewById(R.id.interest_2)
        val interestThree: TextView = itemView.findViewById(R.id.interest_3)
        val addFriendButton: ImageButton = itemView.findViewById(R.id.add_friend)
        init {
            itemView.setOnClickListener{
                Log.d("Profile Adapter", "Clicked on a profile" )
            }
            addFriendButton.setOnClickListener{
                val db= FirebaseFirestore.getInstance()
                var usersDb = db.collection("users").get()
                usersDb.addOnSuccessListener { documents ->
                    for (document in documents) {
                        var userid = document.id
                        db.collection("users").document(document.id).collection("profile")
                            .document("public").get().addOnSuccessListener { document ->
                            var profile_info = document.get("profile") as Map<String, String>?
                            if (profileName.text == profile_info?.get("name")) {
                                val friendRequestInfo = hashMapOf(
                                    "avatarURL" to profile_info?.get("avatarURL"),
                                    "createdAt" to Timestamp(Date()),
                                    "friendId" to userid,
                                    "receiverId" to userid,
                                    "senderId" to "received",
                                    "updatedAt" to Timestamp(Date()),
                                    "username" to profile_info?.get("username")
                                )
                                db.collection("users").document("user-1")
                                    .collection("friends").document(userid).set(friendRequestInfo)
                            }
                        }
                    }
                }

            }
        }
    }

}