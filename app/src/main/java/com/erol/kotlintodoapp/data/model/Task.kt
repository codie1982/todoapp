package com.erol.kotlintodoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erol.kotlintodoapp.enum.TaskStateEnum
import com.erol.kotlintodoapp.enum.TaskTypeEnum
import com.erol.kotlintodoapp.enum.TypeofCompletionEnum
import kotlin.random.Random

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "task_uuid")
    var task_uuid: String,
    @ColumnInfo(name = "description")
    var description:String,
    @ColumnInfo(name = "category_id")
    var category_id:Int,
    @ColumnInfo(name = "group_id")
    var group_id:Int,
    @ColumnInfo(name = "typeofcompletion")//manuel,byTime,byLocation,byCost
    var typeofcompletion: String,
    @ColumnInfo(name = "state") //complete,uncomplete
    var state:String,
    @ColumnInfo(name = "type")
    var type:String,
    @ColumnInfo(name = "crDate")
    var crDate:Long,
    @ColumnInfo(name = "userid")
    var userid:Int,
) {
}