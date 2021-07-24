package com.erol.kotlintodoapp.data.source.local.Repository

import androidx.lifecycle.LiveData
import com.erol.kotlintodoapp.data.model.Task
import com.erol.kotlintodoapp.data.source.local.dao.TaskDao

class TaskRepository(var taskDao : TaskDao){
    fun addTask(task:Task):Long{
    return taskDao.addNewTask(task)
    }

    fun getAllTask(): LiveData<List<Task>> {
        return taskDao.getAllTask()
    }

    fun getTaskFromCategory(id: Int): LiveData<List<Task>> {
        return taskDao.getTaskFromCategory(id)
    }

    fun setTaskComplete(id: Int){
        taskDao.setTaskComplete(id)
    }
}