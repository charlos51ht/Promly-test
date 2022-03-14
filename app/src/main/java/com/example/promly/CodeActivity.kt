package com.example.promly

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CodeActivity: AppCompatActivity() {

    //declare widgets that will be interacted with in the code
    private lateinit var respectCode : TextView
    private lateinit var codeNote : TextView
    private lateinit var agreeButton :Button
    private lateinit var checkBox: CheckBox


    /**
     * This is the equivalent to the "main" function in other languages
     * Note: Do not write code above "setContentView", it will crash the code
     */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //this connects the layout -> code, allows access to widgets
            setContentView(R.layout.activity_code)

            //initialize widgets
            respectCode = findViewById(R.id.respect_code)
            codeNote = findViewById(R.id.code_note)
            agreeButton = findViewById(R.id.agreeButton)
            checkBox = findViewById(R.id.checkbox)

            addCodeTheme()

        //sets a listener to the checkbox, which lets the app know that it has been clicked
        checkBox.setOnClickListener{

                //agreeButton is unclickable in the layout file so that users are forced to check the checkbox
                //after the app recognizes that checkbox has been clicked, the agreeButton is enabled
                agreeButton.isEnabled = true

                //agreeButton is set to gray to indicate it is unclickable in layout
                //this line sets the button back to original color
                agreeButton.background = getDrawable(R.drawable.gradient_button)

                //sets a listener to the agree button, which lets the app know that it has been clicked
                agreeButton.setOnClickListener {

                    //intents are used to send messages to other components, like another activity
                    //this code is sending a message to the IntroActivity
                    val intent = Intent(this, IntroActivity::class.java)

                    //starts the called activity
                    startActivity(intent)
                }
            }


        }
    //example comment

    /**
     * This method contains code that implements the themes of Promly onto this page
     * For example, adds gradient to the action/navigation bar,
     * adds gradient to "Respect Code" string, etc
     * These are hardcoded b/c they can't be implemented on layout files yet
     */
    private fun addCodeTheme(){
            //this line sets the action bar background to the "navbar_gradient" drawable
            supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_navbar, null))


            //this section will apply a gradient color to the "Respect Code" textview, until there is another solution
            val paint = respectCode.paint
            val width = paint.measureText(respectCode.text.toString())
            val textShader: Shader = LinearGradient(0f, 0f, width, respectCode.textSize, intArrayOf(
                Color.parseColor("#AA57FF"),
                Color.parseColor("#EB25AF"),
                Color.parseColor("#FF1696"),
                Color.parseColor("#FA880C")
            ), null, Shader.TileMode.CLAMP)

            respectCode.paint.shader = textShader

            //this section applies purple to "terms and conditions" and "privacy policy"
            val codeNoteText = "By signing up, you agree to the terms and conditions and privacy policy."
            val codeNoteColored = SpannableString(codeNoteText)

            val purple = ForegroundColorSpan(resources.getColor(R.color.brand_purple))
            val white = ForegroundColorSpan(Color.WHITE)

            codeNoteColored.setSpan(purple, 32, codeNoteText.length - 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            codeNoteColored.setSpan(white, 53, 56, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)

            codeNote.text = codeNoteColored
        }

    }
