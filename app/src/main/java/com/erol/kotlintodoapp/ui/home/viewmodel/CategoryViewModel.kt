package com.erol.kotlintodoapp.ui.home.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.data.model.Category
import com.erol.kotlintodoapp.data.source.ApplicationDB
import com.erol.kotlintodoapp.data.source.local.Repository.CategoryRepository
import com.erol.kotlintodoapp.preferences.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var categoryRepository: CategoryRepository
    lateinit var allCategory: LiveData<List<Category>>
    lateinit var selectedCategory :LiveData<Category>
    var _activeCategoryid = 0

    init {
        categoryRepository =
            CategoryRepository(ApplicationDB.getDatabase(application).categoryDao())
    }

    fun getAllCateogry(context: Context) {
        viewModelScope.launch {
            var preferences: Preferences = Preferences()
            var fs = preferences.getFirstSetup(context)
            if (fs != null) {
                if (!fs) {
                    var standartCategoryList =  context.resources.getStringArray(R.array.category_list_items)

                    for (name in standartCategoryList) addCategory(Category(0,
                        Random.Default.nextInt(1000).toString(),
                        name,
                        "aciklama",
                        "private",
                        "#f44336"))
                    preferences.setFirstSetup(context)
                }
            }
            allCategory =  categoryRepository.getAllCategory()
        }
    }

    fun addCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.addCategory(category)
        }
    }
    fun setActiveCategory(id:Int){
        _activeCategoryid = id
    }
    fun getActiveCategoryid(): Int {
        return this._activeCategoryid
    }
    fun getCategoryPositionfromName(category_name:String): Category? {
        for (category in allCategory.value!!){
           if( category.category_name == category_name){
               return category
           }
        }
        return null
    }

    fun getActiveCategory(): Category? {
        return this.allCategory.value?.get(this._activeCategoryid)
    }
}