package com.example.dvma

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or


class PinLogin : AppCompatActivity() {
    val PREF = "PrefLogin"
    val INITIALISED = "initialised"
    val USERNAME = "UsernamePref"
    val PASSWORD = "PasswordPref"
    private val sourcePath : String = "/data/data/com.example.dvma/cache/pin.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_login)

        val login = findViewById<Button>(R.id.login)
        val inputPIN = findViewById<EditText>(R.id.editTextPIN)
        val hashTxt = findViewById<TextView>(R.id.hash)


        if(!File("/data/data/com.example.dvma/shared_prefs/PrefLogin.xml").exists()) {
            //initialise shared prefs
            var sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE)

            //shared prefs editor
            var edit = sharedPref.edit()

            edit.putBoolean(INITIALISED, true)
            edit.putString(USERNAME, "micHa1l")
            edit.putString(PASSWORD, "busterTheDog1")
            edit.commit()
        }

        //if the pin file doesn't exist, encrypt a random 4 digit number and write it
        //to the file
        if (!File(sourcePath).exists()) {
            var random = Random()
            var pin : String = String.format("%04d", random.nextInt(10000))
            println(pin) //not in final build
            val hash = encrypt(pin)
            writePIN(hash)
        }


        login.setOnClickListener {
            val PIN = inputPIN.text.toString()

            var hash : String = encrypt(PIN)
            //hashTxt.text = hash
            if (File(sourcePath).exists()) {
                if (checkPin(hash)) {
                    hashTxt.text = "You're in!"
                    val i : Intent = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }
                else {
                    hashTxt.text = "it didn't work :("
                    inputPIN.text.clear()
                }
            }
        }

        //this just makes sure that when a pin is entered the enter button on the keyboard calls the onclick function
        //and doesn't minimise the keyboard
        inputPIN.setOnEditorActionListener(OnEditorActionListener { textView, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                login.callOnClick()
            }
            true
        })
    }

    private fun checkPin(hash: String): Boolean {
        val check : String = File(sourcePath).readText(Charsets.UTF_8)
        //just a simple check to validate the pin
        println("Hash: $hash")
        println("Check: $check")
        return check == hash
    }

    private fun writePIN(hash: String) {
        File(sourcePath).bufferedWriter().use { out ->
            out.write(hash)
        }
    }

    /**
     * SHA-512, the algorithm used to encrypt the PIN, is typically not the type of hashing algorithm
     * that should be used to store sensitive information such as passwords, instead, using something
     * like bcrypt (http://bcrypt.sourceforge.net/), would be sufficient.
     *
     * if you're interested, check this link out:
     * https://security.stackexchange.com/questions/52041/is-using-sha-512-for-storing-passwords-tolerable
     */
    fun encrypt(input: String) : String {
        //removed salt so that user can bruteforce pin from the raw hash
        try {
            val md : MessageDigest = MessageDigest.getInstance("SHA-512")
            //toByteArray defaults to UTF-8
            val str : ByteArray = md.digest(input.toByteArray())
            val sb : StringBuilder = StringBuilder()

            //for every character
            for (i in str.indices) {
                //add the result of (current character byte bitwise and 1111 1111 or 1 0000 0000)
                sb.append(
                    (str[i].and(0xff.toByte())).or(0x100.toByte()).toInt().toString(16).substring(
                        1
                    )
                )
            }

            return sb.toString()
        }
        catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

//    fun getSalt() : ByteArray {
//        val rnd = SecureRandom()
//        val salt = ByteArray(16)
//
//        rnd.nextBytes(salt)
//        return salt
//    }
}