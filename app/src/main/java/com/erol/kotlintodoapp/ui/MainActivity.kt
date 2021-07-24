package com.erol.kotlintodoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavigatorProvider
import androidx.navigation.findNavController
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.preferences.Preferences
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
     val  preferences:Preferences=Preferences()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences.start(baseContext)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        var navController = findNavController(R.id.fragment)
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }*/

}




