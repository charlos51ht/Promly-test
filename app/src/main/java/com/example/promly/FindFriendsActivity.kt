package com.example.promly

import android.content.ContentValues.TAG
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promly.TwentyByTwenty.Goal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso



class FindFriendsActivity : AppCompatActivity() {

    private lateinit var ff_toolbar : Toolbar
    private lateinit var search_bar : EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileAdapter
    private lateinit var interest_pills: ScrollView
    private lateinit var filters: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)
        ff_toolbar = findViewById(R.id.toolbar_ff)
        interest_pills = findViewById(R.id.interest_scroll)
        filters = findViewById(R.id.filters)
        filters.setVisibility(View.GONE)
        setSupportActionBar(ff_toolbar)
        search_bar = findViewById(R.id.search_friends)
        var currentQueryType = "name"
        var query_description = ""
        search_bar.onFocusChangeListener= View.OnFocusChangeListener{ view, hasFocus->
            if (hasFocus)
            {
                //collapse navbar
                //update search results as user types
                ff_toolbar.setVisibility(View.GONE)
                filters.setVisibility(View.VISIBLE)
                interest_pills.setVisibility(View.GONE)
                //Make filter recycleable
                //sets recycler view to one that is filtered by name
            }
        }
        var ignore = false
        search_bar.doOnTextChanged { text, start, before, count ->
            var query_profiles = java.util.ArrayList<Profile>()
            for(profile in profiles) {
                Log.d("security",query_profiles.size.toString())
                if(profile.profile?.get("name")?.contains(text.toString()) == true)
                    query_profiles.add(profile)
                Log.d("security",text.toString())
            }
            adapter = ProfileAdapter(query_profiles)
            recyclerView.adapter = adapter

        }

        recyclerView = findViewById(R.id.expand_your_circle_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true);

        populateList()

    }

    private fun populateList(){
        val db= FirebaseFirestore.getInstance()
        var usersDb = db.collection("users").limit(20).get()
        usersDb.addOnSuccessListener { documents ->
            for (document in documents)
                db.collection("users").document(document.id).collection("profile")
                    .document("public").get().addOnSuccessListener { document ->
                     Log.i("Check DB", "${document.id}=> ${document.data}");
                        if(document != null){
                            var profile = document.get("profile") as Map<String, String>?
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
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    if(search_bar.text.toString()=="")
                    {
                        profiles = java.util.ArrayList<Profile>()
                        populateList()
                        interest_pills.setVisibility(View.VISIBLE)
                        filters.setVisibility(View.GONE)
                        ff_toolbar.setVisibility(View.VISIBLE)
                      //  card_test.setVisibility(View.VISIBLE)
                        //set adapter to the photo and interests form
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
    companion object {
        var profiles = java.util.ArrayList<Profile>()
    }
}
