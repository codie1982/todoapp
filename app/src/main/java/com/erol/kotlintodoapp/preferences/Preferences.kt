package com.erol.kotlintodoapp.preferences

import android.content.Context

class Preferences {
    val FIRST_SETUP_KEY = "FIRST_SETUP"
    var FIRST_SETUP: Boolean = false
    val preferencesUtil: PreferencesUtil = PreferencesUtil()
    fun start(context: Context) {
        FIRST_SETUP.let {
            preferencesUtil.getBooleanPreferences(
                context,
                FIRST_SETUP_KEY,
                FIRST_SETUP
            )
        }
    }

    fun getFirstSetup(context: Context): Boolean? {
        return preferencesUtil.getBooleanPreferences(context, FIRST_SETUP_KEY, FIRST_SETUP)
    }

    fun setFirstSetup(context: Context) {
        preferencesUtil.setBooleanPreferences(context, FIRST_SETUP_KEY, true)
    }
}