package com.erol.kotlintodoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var category_uuid:String,
    var category_name:String,
    var category_description:String,
    var type:String,
    var color:String,
) {
}