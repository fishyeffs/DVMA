package com.example.dvma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    //"/data/data/com.example.dvma/shared_prefs

    //var names = resources.getStringArray(R.array.contacts)
    //var msg = resources.getStringArray(R.array.msg_beginning)
    //var img = arrayOf(Int)
    //var profileImg = R.drawable.ic_baseline_account_circle_24

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var names = resources.getStringArray(R.array.contacts).toMutableList()
        var msgs = resources.getStringArray(R.array.msg_beginning).toMutableList()
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = MyAdapter(this, names, msgs, 0)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.nav_account -> {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}