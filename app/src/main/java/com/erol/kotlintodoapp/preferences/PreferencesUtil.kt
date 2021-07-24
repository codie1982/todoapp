package com.erol.kotlintodoapp.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.erol.kotlintodoapp.constant.Constant
import kotlin.Exception

class PreferencesUtil {
    val PREF_NAME = "com.erol.kotlintodoapp"
    fun getStringPreference(context: Context,key:String,defaultValue:String):String?{
        try {
            var sharedPreferences:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            return sharedPreferences.getString(key,defaultValue)
        }catch (ex:Exception){
            Log.d("TAG","String Değer Okunurken Hata Oluştu :  ${ex.message}")
            return null
        }
    }

    fun getBooleanPreferences(context: Context,key:String,defaultValue:Boolean):Boolean?{
        try {
            var sharedPreferences:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(key,defaultValue)
        }catch (ex:Exception){
            Log.d("TAG","Bool Değer Okunurken Hata Oluştu :  ${ex.message}")
            return null
        }
    }

    fun setStringPreferences(context: Context,key:String,value:String){
        try {
            var sharedPreferences:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            var editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(key,value)
            editor.apply()
        }catch (ex:Exception){
            Log.d("TAG","${key} - String Değer Kayıt edilirken Hata Oluştu : ${ex.message}")
        }
    }
    fun setBooleanPreferences(context: Context,key:String,value:Boolean){
        try {
            var sharedPreferences:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            var editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(key,value)
            editor.apply()
            Log.d("TAG","${key} - ${value} - Ayarlandı")
        }catch (ex:Exception){
            Log.d("TAG","${key} - Bool Değer Kayıt edilirken Hata Oluştu : ${ex.message}")
        }

    }
}