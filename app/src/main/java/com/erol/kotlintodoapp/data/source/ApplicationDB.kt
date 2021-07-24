package com.erol.kotlintodoapp.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erol.kotlintodoapp.data.model.Category
import com.erol.kotlintodoapp.data.model.Task
import com.erol.kotlintodoapp.data.source.local.dao.CategoryDao
import com.erol.kotlintodoapp.data.source.local.dao.TaskDao

@Database(entities = [Task::class,Category::class],version = 1,exportSchema = false)
abstract class ApplicationDB: RoomDatabase() {
    abstract fun categoryDao():CategoryDao
    abstract fun taskDao():TaskDao
    companion object{
        private val DATABASE_NAME="application_db"

        @Volatile
        private var INSTANCE : ApplicationDB?=null

        fun getDatabase(context: Context): ApplicationDB {
            var tempDB = INSTANCE
            if (tempDB != null){
                return tempDB
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDB::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}