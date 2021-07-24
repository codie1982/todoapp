package com.erol.kotlintodoapp.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.ui.home.viewmodel.ShareViewModel

class CreateNewCategoryDialog() : DialogFragment() {
    private lateinit var addBtn:Button
    private lateinit var categoryName:String
    private lateinit var shareViewModel: ShareViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         super.onCreateDialog(savedInstanceState)

        var builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        var inflater : LayoutInflater = activity?.layoutInflater!!
        var view: View = inflater.inflate(R.layout.new_category_modal,null)
        builder.setView(view)
        addBtn = view.findViewById(R.id.btnAddNewCategory)
        addBtn.setOnClickListener {
            categoryName = view.findViewById<EditText>(R.id.edtxtCategoryName).text.toString()
            if(categoryName != ""){
                shareViewModel.setCategoryName(categoryName)
                this.dismiss()
            }else {
                println("Bir Kategori İsmi giriniz.")
            }
        }
        return builder.create();
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        shareViewModel = activity?.let { ViewModelProvider(it).get(ShareViewModel::class.java) }!!
    }
}
