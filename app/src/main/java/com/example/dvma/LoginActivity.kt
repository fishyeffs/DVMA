package com.example.dvma

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    val PREF = "PrefLogin"
    val USERNAME = "UsernamePref"
    val PASSWORD = "PasswordPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.unameLogin)
        val uname = findViewById<EditText>(R.id.editUsername)
        val pass = findViewById<EditText>(R.id.editPassword)

        //initialise shared prefs
        var sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        login.setOnClickListener {
            //specified intended receiver is com.example.dvma
            val intent = packageManager.getLaunchIntentForPackage("com.example.dvma")

            //shared prefs editor
            var edit = sharedPref.edit()

            edit.putString(USERNAME, uname.text.toString())
            edit.putString(PASSWORD, pass.text.toString())
            edit.commit()

            //data is sensitive and unencrypted, should be encrypted/stored correctly instead
            intent?.apply {
                putExtra("Username", uname.text.toString())
                putExtra("Password", pass.text.toString())
            }

            startActivity(intent)
        }
    }
}