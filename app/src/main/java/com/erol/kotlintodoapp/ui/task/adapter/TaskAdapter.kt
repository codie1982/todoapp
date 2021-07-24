package com.erol.kotlintodoapp.ui.task.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.data.model.Task
import com.erol.kotlintodoapp.ui.task.adapter.TaskAdapter.*
import kotlinx.android.synthetic.main.task_item.view.*
import kotlinx.android.synthetic.main.task_item.view.txtTaskDescription

class TaskAdapter(var taskList:List<Task>,
                  var taskListener:TaskListener
): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false),
            taskListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.task_description.text = taskList[position].description
        Log.d("TAG","taskList[position].state ")
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    fun setData(allTask : List<Task>){
        taskList = allTask
        notifyDataSetChanged()
    }

    class TaskViewHolder(item: View,taskListener: TaskListener):RecyclerView.ViewHolder(item){
        var task_description :TextView
        var chkComplete : CheckBox
        init {
            task_description = item.txtTaskDescription
            chkComplete = item.chkComplete
            chkComplete.setOnCheckedChangeListener { buttonView, isChecked ->
                taskListener.onCompleteTaskItem(adapterPosition,isChecked)
            }
        }



    }
    interface TaskListener{
        fun onCompleteTaskItem(position: Int,checked:Boolean)
    }
}