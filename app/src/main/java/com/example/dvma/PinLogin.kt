package com.example.dvma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import kotlin.experimental.and
import kotlin.experimental.or

class PinLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_login)

        val login = findViewById<Button>(R.id.login)
        val inputPIN = findViewById<EditText>(R.id.editTextPIN)
        val hashTxt = findViewById<TextView>(R.id.hash)


        val PIN = inputPIN.text.toString()
        val salt = getSalt()
        val hash : String = encrypt(PIN, salt)

        //works once
        login.setOnClickListener {
            hashTxt.text = hash
        }
    }

    fun checkPin() {

    }

    fun encrypt(input : String, salt : ByteArray) : String {
        var digest : String = ""
        try {
            var md : MessageDigest = MessageDigest.getInstance("SHA-512")
            md.update(salt)

            //toByteArray defaults to UTF-8
            var str : ByteArray = md.digest(input.toByteArray())

            var sb : StringBuilder = StringBuilder()

            for (i in 0 until str.size) {
                sb.append(Integer.toString((str.get(i).and(0xff.toByte())).or(0x100.toByte()).toInt(), 16).substring(1))
            }

            var result : String = sb.toString()

            return result
        }
        catch (e : Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun getSalt() : ByteArray {
        var rnd : SecureRandom = SecureRandom()
        var salt : ByteArray = ByteArray(16)

        rnd.nextBytes(salt)
        return salt
    }
}