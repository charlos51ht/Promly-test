package com.example.promly

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class FindFriendsActivity : AppCompatActivity() {

    private lateinit var ff_toolbar : Toolbar
    private lateinit var search_bar : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var home_friends: ImageButton
    private lateinit var adapter: ProfileAdapter
    private lateinit var interest_pills: HorizontalScrollView
    private lateinit var filters: LinearLayout
    private lateinit var name_filter: TextView
    private lateinit var hashtag_filter: TextView
    private lateinit var school_filter: TextView
    private lateinit var cancel_text: TextView
    var profiles = java.util.ArrayList<Profile>()
    private var queryType : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)
        search_bar = findViewById(R.id.search_friends)
        ff_toolbar = findViewById(R.id.toolbar_ff)
        interest_pills = findViewById(R.id.interest_scroll)
        name_filter = findViewById(R.id.people_filter)
        cancel_text = findViewById(R.id.cancel_text)
        cancel_text.setVisibility(View.GONE)
        cancel_text.setOnClickListener{
            profiles = java.util.ArrayList<Profile>()
            populateList()
            interest_pills.setVisibility(View.VISIBLE)
            filters.setVisibility(View.GONE)
            ff_toolbar.setVisibility(View.VISIBLE)
            cancel_text.setVisibility(View.GONE)
            currentFocus?.clearFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.getWindowToken(), 0)
        }
        name_filter.setOnClickListener(){
            queryType = 0
            var query_profiles = querySelect(search_bar.text.toString())
            adapter = ProfileAdapter(query_profiles)
            recyclerView.adapter = adapter
        }
        hashtag_filter = findViewById(R.id.interests_filter)
        hashtag_filter.setOnClickListener(){
            queryType = 1
            var query_profiles = querySelect(search_bar.text.toString())
            adapter = ProfileAdapter(query_profiles)
            recyclerView.adapter = adapter
        }
        school_filter = findViewById(R.id.school_filter)
        school_filter.setOnClickListener(){
            queryType = 2
            var query_profiles = querySelect(search_bar.text.toString())
            adapter = ProfileAdapter(query_profiles)
            recyclerView.adapter = adapter
        }
        filters = findViewById(R.id.filters)
        filters.setVisibility(View.GONE)
        setSupportActionBar(ff_toolbar)
        search_bar.onFocusChangeListener= View.OnFocusChangeListener{ view, hasFocus->
            if (hasFocus)
            {
                //collapse navbar
                //update search results as user types
                ff_toolbar.setVisibility(View.GONE)
                filters.setVisibility(View.VISIBLE)
                cancel_text.setVisibility(View.VISIBLE)
                interest_pills.setVisibility(View.GONE)
                //Make filter recyclable
                //sets recycler view to one that is filtered by name
            }
        }
        search_bar.doOnTextChanged { text, start, before, count ->
             var query_profiles = querySelect(text.toString())
            adapter = ProfileAdapter(query_profiles)
            recyclerView.adapter = adapter

        }

        recyclerView = findViewById(R.id.expand_your_circle_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)

        querySelect("")
        populateList()
        home_friends = findViewById(R.id.home_friend)
        home_friends.setOnClickListener{
            val homeIntent = Intent(this, HomePageActivity::class.java)
            startActivity(homeIntent)
        }

    }

    private fun populateList(){
        val db= FirebaseFirestore.getInstance()
        var usersDb = db.collection("users").limit(20).get()
        usersDb.addOnSuccessListener { documents ->
            for (document in documents)
                if(document.id!=intent.getStringExtra("user-id"))
                    db.collection("users").document(document.id).collection("profile")
                    .document("public").get().addOnSuccessListener { document ->
                     Log.i("Check DB", "${document.id}=> ${document.data}")
                        if(document != null){
                                Log.d("TAG", "Document Exists!!!")
                                profiles.add(
                                    Profile(
                                        document.get("interests") as ArrayList<String>?,
                                        document.get("profile") as Map<String, String>?,
                                        document.get("school") as Map<String, String>?
                                    )
                                )
                                Log.d("Check Size", profiles.size.toString())
                        }else{
                            Log.d("TAG", "No Such Document!!!")
                        }
                        adapter = ProfileAdapter(profiles)
                        recyclerView.adapter = adapter
                }
                .addOnFailureListener { exception ->
                     Log.d(TAG, "get failed with ", exception)
                }


        }



    }
   override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                val outRect1 = Rect()
                filters.getGlobalVisibleRect(outRect1)
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())&&!outRect1.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    if(search_bar.text.toString()=="")
                    {
                        profiles = java.util.ArrayList<Profile>()
                        populateList()
                        interest_pills.setVisibility(View.VISIBLE)
                        filters.setVisibility(View.GONE)
                        cancel_text.setVisibility(View.GONE)
                        ff_toolbar.setVisibility(View.VISIBLE)
                      //  card_test.setVisibility(View.VISIBLE)
                        //set adapter to the photo and interests form
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
    private fun querySelect(text:String): java.util.ArrayList<Profile> {
        var query_profiles = java.util.ArrayList<Profile>()
        if(queryType == 0)
        {
            name_filter.setTextColor(resources.getColor(R.color.brand_pink))
            hashtag_filter.setTextColor(resources.getColor(R.color.white))
            school_filter.setTextColor(resources.getColor(R.color.white))
            for(profile in profiles) {
                if(profile.profile?.get("name")?.lowercase()?.contains(text.lowercase()) == true)
                    query_profiles.add(profile)
            }
        }
        if(queryType == 1)
        {
            name_filter.setTextColor(resources.getColor(R.color.white))
            hashtag_filter.setTextColor(resources.getColor(R.color.brand_pink))
            school_filter.setTextColor(resources.getColor(R.color.white))
            for(profile in profiles) {
                var interests = profile.interests
                if (interests != null) {
                    for(interest in interests)
                        if(interest.lowercase().contains(text.lowercase())) {
                            query_profiles.add(profile)
                            break
                        }
                }
            }
        }
        if(queryType == 2)
        {
            name_filter.setTextColor(resources.getColor(R.color.white))
            hashtag_filter.setTextColor(resources.getColor(R.color.white))
            school_filter.setTextColor(resources.getColor(R.color.brand_pink))
            /*no school data added to database yet*/
            for(profile in profiles) {
                if(profile.school?.get("name")?.lowercase()?.contains(text.lowercase()) == true)
                    query_profiles.add(profile)
            }
        }
        return query_profiles
    }
}
