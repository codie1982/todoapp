package com.erol.kotlintodoapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erol.kotlintodoapp.data.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNewTask(task: Task):Long

    @Query("UPDATE task_table SET state='COMPLETE' WHERE id=:id")
    fun setTaskComplete (id: Int)

    @Query("SELECT * FROM task_table WHERE state='UNCOMPLETE' ")
    fun getAllTask():LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE category_id=:id AND state='UNCOMPLETE'")
    fun getTaskFromCategory(id: Int): LiveData<List<Task>>

}
