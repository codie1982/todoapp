package com.erol.kotlintodoapp.ui.contact.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.lib.ContactDTO
import com.erol.kotlintodoapp.ui.MainActivity
import com.erol.kotlintodoapp.ui.contact.ContactViewModel

class ContactAdapter(var contactList: ArrayList<ContactDTO>,var contactListener:ContactListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var contactViewModel :ContactViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        //holder.contact_image = contactList[position].image
        holder.contact_name.text = contactList[position].name
        holder.contact_phone.text = contactList[position].number
        var contactid = contactList[position].id

        holder.checkContact.setOnCheckedChangeListener { buttonView, isChecked ->
            contactListener.selectedContact(contactList[position],isChecked)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
    class ContactViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        //var contact_image:ImageView
        var contact_name:TextView
        var contact_phone:TextView
        var checkContact : CheckBox

        init {
            contact_name = v.findViewById(R.id.txtContactName)
            contact_phone = v.findViewById(R.id.txtContactPhoneNumber)
            checkContact = v.findViewById(R.id.checkSelectedContact)

        }

    }


}
interface ContactListener{
    fun selectedContact(contact:ContactDTO,checked:Boolean)
}