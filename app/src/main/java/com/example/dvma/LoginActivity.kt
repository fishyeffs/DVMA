package com.example.dvma

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    val PREF = "PrefLogin"
    val INITIALISED = "initialised"
    val USERNAME = "UsernamePref"
    val PASSWORD = "PasswordPref"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.unameLogin)
        val uname = findViewById<EditText>(R.id.editUsername)
        val pass = findViewById<EditText>(R.id.editPassword)

        login.setOnClickListener {
            var sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE)

            if (uname.text.toString() == sharedPref.getString(USERNAME, "") && pass.text.toString() == sharedPref.getString(PASSWORD, "")) {
                val edit = sharedPref.edit()
                edit.putBoolean(INITIALISED, true)
                edit.apply()
                //calling an action rather than a destination
                val intent = Intent("com.example.dvma.LOGIN")

                //data is sensitive and unencrypted, should be encrypted/stored correctly instead
                intent?.apply {
                    putExtra("Username", uname.text.toString())
                    putExtra("Password", pass.text.toString())
                }

                startActivity(intent)
                startActivity(Intent(this, MainActivity::class.java))
            }
            else {
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(this, "Login attempt failed", duration)
            }
        }
    }
}