package com.erol.kotlintodoapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel: ViewModel() {
    private var categoryName : MutableLiveData<CharSequence> = MutableLiveData<CharSequence>()
    fun setCategoryName(input :CharSequence){
        categoryName.value = input
    }
    fun getCategoryName():LiveData<CharSequence>{
        return categoryName
    }
}