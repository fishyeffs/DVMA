package com.example.dvma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
}