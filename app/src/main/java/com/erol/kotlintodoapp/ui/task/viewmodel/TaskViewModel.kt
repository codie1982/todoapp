package com.erol.kotlintodoapp.ui.task.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.erol.kotlintodoapp.data.model.Task
import com.erol.kotlintodoapp.data.source.ApplicationDB
import com.erol.kotlintodoapp.data.source.local.Repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var taskRepository: TaskRepository
    lateinit var allTask: LiveData<List<Task>>
    var insertId :Long = -1

    init {
        taskRepository = TaskRepository(ApplicationDB.getDatabase(application).taskDao())
    }

    fun addNewTask(task: Task): MutableLiveData<Long> {
        val result = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            insertId = taskRepository.addTask(task)
            result.postValue(insertId)
        }
        return result
    }

    fun getAllTask() {
        viewModelScope.launch {
            allTask = taskRepository.getAllTask()
        }
    }
    fun getTaskFromCategory(id:Int) {
        viewModelScope.launch {
            allTask = taskRepository.getTaskFromCategory(id)
        }
    }

    fun setTaskComplete(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.setTaskComplete(id)
        }
    }


}