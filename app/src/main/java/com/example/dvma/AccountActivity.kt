package com.example.dvma

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AccountActivity : AppCompatActivity() {
    val PREF = "PrefLogin"
    val USERNAME = "UsernamePref"
    val PASSWORD = "PasswordPref"
    val INITIALISED = "initialised"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        var sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE)
        var editor = sharedPref.edit()

        val usernameTextView = findViewById<TextView>(R.id.uname)
        usernameTextView.text = sharedPref.getString(USERNAME, "")
        val sign_out = findViewById<Button>(R.id.sign_out)

        sign_out.setOnClickListener {
            editor.putBoolean(INITIALISED, false)
            editor.apply()
            val intent = Intent(this, PinLogin::class.java)
            startActivity(intent)
        }
    }
}