package com.example.promly

import android.content.ContentValues.TAG
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


private lateinit var ff_toolbar : Toolbar
private lateinit var search_bar : EditText

private lateinit var card_test  : CardView
class FindFriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)
        ff_toolbar = findViewById(R.id.toolbar_ff)
        setSupportActionBar(ff_toolbar)
        card_test = findViewById(R.id.card_test)
        search_bar = findViewById(R.id.search_friends)
        search_bar.onFocusChangeListener= View.OnFocusChangeListener{ view, hasFocus->
            if (hasFocus)
            {
                //collapse navbar
                //update search results as user types
                ff_toolbar.setVisibility(View.GONE)
                card_test.setVisibility(View.GONE)
                //Make filter recycleable
                //sets recycler view to one that is filtered by name
            }
        }
        var profiles = ArrayList<Profile>()
        //Database Logic
        var db= FirebaseFirestore.getInstance()
        var users_db = db.collection("users").limit(20).get()
        users_db.addOnSuccessListener { documents->
            for(document in documents)
                db.collection("users").document(document.id).collection("profile").document("public").get().addOnSuccessListener{ document->
                    Log.d(TAG, "${document.id}=> ${document.data}");
                    profiles.add(Profile(document.get("interests") as ArrayList<String>, document.get("profile") as Map<String, String>?, document.get("school") as Map<String, String>?))
                    Picasso.get().load(profiles.get(0).profile?.get("avatarURL")).fit().into(card_test.findViewById(R.id.friend_image) as ImageView)
                    findViewById<TextView>(R.id.friend_name).text = profiles.get(0).profile?.get("name")
                    findViewById<TextView>(R.id.interest_1).text = "#"+profiles.get(0).interests?.get(0);
                    findViewById<TextView>(R.id.interest_2).text = "#"+profiles.get(0).interests?.get(1);
                    findViewById<TextView>(R.id.interest_3).text = "#"+profiles.get(0).interests?.get(2);
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
                        ff_toolbar.setVisibility(View.VISIBLE)
                        card_test.setVisibility(View.VISIBLE)
                        //set adapter to the photo and interests form
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}