package com.example.dvma

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
    private val sourcePath : String = "/data/data/com.example.dvma/cache/pin.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_login)

        val login = findViewById<Button>(R.id.login)
        val inputPIN = findViewById<EditText>(R.id.editTextPIN)
        val hashTxt = findViewById<TextView>(R.id.hash)

        if (!File(sourcePath).exists()) {
            var random = Random()
            var pin : String = String.format("%04d", random.nextInt(10000))
            println(pin) //not in final build
            val hash = encrypt(pin)
            writePIN(hash)
        }

        //works once
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
                }
            }
        }

        inputPIN.setOnEditorActionListener(OnEditorActionListener { textView, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                login.callOnClick()
                true
            }
            true
        })
    }

    private fun checkPin(hash: String): Boolean {
        val check : String = File(sourcePath).readText(Charsets.UTF_8)

        println("Hash: $hash")
        println("Check: $check")
        return check == hash
    }

    private fun writePIN(hash: String) {
        File(sourcePath).bufferedWriter().use { out ->
            //out.write("\n")
            out.write(hash)
        }
    }

    fun encrypt(input: String) : String {
        try {
            val md : MessageDigest = MessageDigest.getInstance("SHA-512")
            //md.update(salt)

            //toByteArray defaults to UTF-8
            val str : ByteArray = md.digest(input.toByteArray())

            val sb : StringBuilder = StringBuilder()

            for (i in str.indices) {
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