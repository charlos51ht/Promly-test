package com.example.promly

import android.content.ContentValues.TAG
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var ff_toolbar : Toolbar
private lateinit var search_bar : EditText

private lateinit var test_text  : TextView
class FindFriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)
        ff_toolbar = findViewById(R.id.toolbar_ff)
        setSupportActionBar(ff_toolbar)
        search_bar = findViewById(R.id.search_friends)
        search_bar.onFocusChangeListener= View.OnFocusChangeListener{ view, hasFocus->
            if (hasFocus)
            {
                //collapse navbar
                //update search results as user types
            }
        }
        var profiles = ArrayList<Profile>()
        //Database Logic
        test_text = findViewById(R.id.TestText)
        var db= FirebaseFirestore.getInstance()
        var users_db = db.collection("users").limit(20).get()
        users_db.addOnSuccessListener { documents->
            for(document in documents)
                db.collection("users").document(document.id).collection("profile").document("public").get().addOnSuccessListener{ document->
                    Log.d(TAG, "${document.id}=> ${document.data}");
                    profiles.add(Profile(document.get("interests") as ArrayList<String>, document.get("profile") as Map<String, String>?, document.get("school") as Map<String, String>?))
                    test_text.text= profiles.get(0).interests?.get(0);
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
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}