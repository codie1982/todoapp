package com.erol.kotlintodoapp.ui.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erol.kotlintodoapp.lib.ContactDTO
import kotlinx.coroutines.launch

class ContactViewModel: ViewModel() {
    private  var selectedContacts : ArrayList<ContactDTO> = ArrayList()

    fun setContactToList(selectedList:ArrayList<ContactDTO>){
        viewModelScope.launch {
            selectedContacts = selectedList
        }
    }
    fun getSelectedContacts(): ArrayList<ContactDTO>? {
        return this.selectedContacts
    }
}