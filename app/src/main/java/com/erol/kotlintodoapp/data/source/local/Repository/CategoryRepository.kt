package com.erol.kotlintodoapp.data.source.local.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.data.model.Category
import com.erol.kotlintodoapp.data.source.local.dao.CategoryDao

class CategoryRepository(var categoryDao : CategoryDao) {

     fun addCategory(category:Category){
        categoryDao.addCategory(category)
    }

    fun getCategoryFromName(category_name:String): Category? {
      var categories = categoryDao.getCategoryIdFromName(category_name)
        return categories.value?.get(0)
    }

     fun getAllCategory():LiveData<List<Category>>{
       return categoryDao.getAllCategory()
    }


}