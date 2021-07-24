package com.erol.kotlintodoapp.ui.contact.fragment

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.lib.ContactDTO
import com.erol.kotlintodoapp.ui.contact.ContactViewModel
import com.erol.kotlintodoapp.ui.contact.adapter.ContactAdapter
import com.erol.kotlintodoapp.ui.contact.adapter.ContactListener
import kotlinx.android.synthetic.main.fragment_contact.view.*

class ContactFragment : Fragment(), ContactListener {

    private  var TEMPContactList:ArrayList<ContactDTO> = ArrayList()
    lateinit var contactViewModel:ContactViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_contact, container, false)
        var contactListXML = view.listContact
        var contactList : ArrayList<ContactDTO> = ArrayList()
        var contacts = context?.let { it.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null) }
        while (contacts?.moveToNext()==true){
            var name =  contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            var number =  contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            var id =  contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID))
            var contactDto = ContactDTO()
            contactDto.name = name
            contactDto.number = number
            contactDto.id = id
            contactList.add(contactDto)
        }
        Log.d("TAG",contactList.size.toString())
        contacts?.close()

        var contactAdapter = ContactAdapter(contactList,this)
        contactListXML.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        contactListXML.adapter = contactAdapter

        var btnBack = view.findViewById(R.id.btnBackContact) as Button
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_contactFragment_to_taskFragment)
        }

        var btnAddContact = view.findViewById(R.id.btnAddContact) as Button
        btnAddContact.setOnClickListener {
            contactViewModel.setContactToList(TEMPContactList)
            getBack()
        }

        return view
    }
    private fun getBack()=findNavController().navigate(R.id.action_contactFragment_to_taskFragment)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactViewModel = activity?.let { ViewModelProvider(it).get(ContactViewModel::class.java) }!!
    }
    override fun selectedContact(contact:ContactDTO,isChecked:Boolean) {
        if(isChecked){
            if(!checkList(contact))
                TEMPContactList.add(contact)

        }else {
            if(checkList(contact))
                TEMPContactList.remove(contact)
        }
    }

    fun checkList(contact: ContactDTO):Boolean{
        var result = false
        for (cn in TEMPContactList){
            if(cn.id == contact.id){
                result=true
            }
        }
        return result
    }
}