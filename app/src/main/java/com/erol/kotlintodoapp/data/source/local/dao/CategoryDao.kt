package com.erol.kotlintodoapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erol.kotlintodoapp.data.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(cateogory:Category)

    @Query("SELECT * FROM category_table")
    fun getAllCategory():LiveData<List<Category>>

    @Query("SELECT * FROM category_table WHERE category_name=:category_name")
    fun getCategoryIdFromName(category_name: String):LiveData<List<Category>>

}