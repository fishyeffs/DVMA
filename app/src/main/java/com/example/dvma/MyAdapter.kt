package com.example.dvma

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_item.view.*

class MyAdapter(private val context : Context, private val contactsList: MutableList<String>, private val msgList: MutableList<String>, private val pfp : Int) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact : Contact = Contact(contactsList[position],msgList[position], pfp)
        holder.set(contact)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        fun set(contact: Contact) {
            itemView.name.text = contact.name
            itemView.msg_text.text = contact.msg
            //itemView.icon = contact.img
            //image setting goes here
        }

    }
}