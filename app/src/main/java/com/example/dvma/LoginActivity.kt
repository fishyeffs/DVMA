package com.example.dvma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.unameLogin)
        val uname = findViewById<EditText>(R.id.editUsername)
        val pass = findViewById<EditText>(R.id.editPassword)
        login.setOnClickListener {
            //specified intended receiver is com.example.dvma
            val intent = packageManager.getLaunchIntentForPackage("com.example.dvma")

            //data is sensitive and unencrypted, should be encrypted/stored correctly instead
            intent?.apply {
                putExtra("Username", uname.text.toString())
                putExtra("Password", pass.text.toString())
            }

            startActivity(intent)
        }
    }
}