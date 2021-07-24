package com.erol.kotlintodoapp.ui.task.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.data.model.Task
import com.erol.kotlintodoapp.ui.home.viewmodel.CategoryViewModel
import com.erol.kotlintodoapp.enum.TaskStateEnum
import com.erol.kotlintodoapp.enum.TaskTypeEnum
import com.erol.kotlintodoapp.enum.TypeofCompletionEnum
import com.erol.kotlintodoapp.lib.ContactDTO
import com.erol.kotlintodoapp.ui.contact.ContactViewModel
import com.erol.kotlintodoapp.ui.contact.fragment.ContactFragment
import com.erol.kotlintodoapp.ui.task.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_task.*
import kotlin.random.Random


class TaskFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var taskViewModel: TaskViewModel
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var contactViewModel:ContactViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_task,container,false)


        //Category List
        var categoryListItems = resources.getStringArray(R.array.category_list_items)
        var categoryAdapter = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,categoryListItems)
        var categoryList:Spinner =  view.findViewById(R.id.spnCategoryList) as Spinner
        categoryList.adapter = categoryAdapter

        var activeCategory = categoryViewModel.getActiveCategory()
        categoryList.setSelection(categoryAdapter.getPosition(activeCategory?.category_name ?:categoryListItems[0]))
        categoryList.onItemSelectedListener = this

        //complete List
        var completeListItems = resources.getStringArray(R.array.complete_list_items)
        var completeAdapter = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,completeListItems)
        var completeList :Spinner = view.findViewById(R.id.spnCompleteList) as Spinner
        completeList.adapter =completeAdapter
        completeList.onItemSelectedListener = this

        //Geri Butonu
        var backButton : Button = view.findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            getBack()
        }
        var contactButton : Button = view.findViewById(R.id.btnReadContact)
        contactButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskFragment_to_contactFragment)
        }

        //Yeni İş Oluştur
        var newTaskButton : Button = view.findViewById(R.id.btnAddTask)
        newTaskButton.setOnClickListener {
            //Toast.makeText(requireContext(), "Seçili Kategori ${selectedCategoryid}", Toast.LENGTH_SHORT).show()
            var selectedCategory =  categoryViewModel.getCategoryPositionfromName(categoryList.selectedItem.toString())
            var typeofcompletion = completeList.selectedItem.toString()

            //Yeni Görev Oluştur
            var task = Task(0,
                Random.Default.nextInt(100).toString(),
                txtTaskDescription.text.toString(),
                selectedCategory?.id!!,
                0,
                TypeofCompletionEnum.MANUEL.name,
                TaskStateEnum.UNCOMPLETE.name,
                TaskTypeEnum.PRIVATE.name,
                System.currentTimeMillis(),
                0)

            var selectedContact = contactViewModel.getSelectedContacts()
            if(selectedContact !=null && selectedContact.size !=0){
            Log.d("TAG","Seçilen Kişiler ${selectedContact[0].name} - ${selectedContact[1].name}")
            }else {
                Log.d("TAG","Seçilen herhangi bir kişi yok")
            }
            taskViewModel.addNewTask(task).observe(viewLifecycleOwner, Observer { insertId
                Log.d("TAG","Eklenen Kayıt ${insertId}")
                //Seçilen Kişileride bu kısımda Ekleyebiliriz
                if(selectedContact !=null && selectedContact.size !=0){

                }
            })

            // getBack()
        }
        return view
    }

    //Geri Dönmek için
    private fun getBack()=findNavController().navigate(R.id.action_taskFragment_to_homeFragment)
    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, p3: Long) {
        when (parent.id){
            R.id.spnCategoryList-> {
                println("Kateogri Listesi")
            }
            R.id.spnCompleteList ->{
                when(position){
                    0-> {
                        println("Manuel Tamamlanma")
                    }
                    1-> {
                        println("Zamana Göre Tamamalanma")
                    /*
                        var timeFragment = TimeFragment()
                        activity?.
                        supportFragmentManager?.
                        beginTransaction()?.
                        replace(R.id.completeFragmentContainer,timeFragment,"timeFragment")?.commit()
                    */
                    }
                    2-> {
                        println("Lokasyona Göre Tamamalanma")
                    /*
                        var locationFragment = LocationFragment()
                        activity?.
                        supportFragmentManager?.
                        beginTransaction()?.
                        replace(R.id.completeFragmentContainer,locationFragment,"locationFragment")?.commit()
                    */
                    }
                    3-> {
                        println("Miktara Göre Tamamalanma")
                    /*
                        var costFragment = CostFragment()
                        activity?.
                        supportFragmentManager?.
                        beginTransaction()?.
                        replace(R.id.completeFragmentContainer,costFragment,"costFragment")?.commit()
                    */
                    }
                }
            }
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        println("Herhangi bir seçim oluşmadı")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        taskViewModel = activity?.let { ViewModelProvider(it).get(TaskViewModel::class.java) }!!
        categoryViewModel = activity?.let { ViewModelProvider(it).get(CategoryViewModel::class.java) }!!
        contactViewModel = activity?.let { ViewModelProvider(it).get(ContactViewModel::class.java) }!!

    }


}