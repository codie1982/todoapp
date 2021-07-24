package com.erol.kotlintodoapp.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.erol.kotlintodoapp.data.model.Category
import com.erol.kotlintodoapp.data.source.ApplicationDB
import com.erol.kotlintodoapp.data.source.local.Repository.CategoryRepository
import com.erol.kotlintodoapp.data.source.local.dao.CategoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):AndroidViewModel(application) {
    init {
    }
}