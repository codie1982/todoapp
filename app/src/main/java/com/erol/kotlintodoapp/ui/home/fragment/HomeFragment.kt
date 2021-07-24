package com.erol.kotlintodoapp.ui.home.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.data.model.Category
import com.erol.kotlintodoapp.data.model.Task
import com.erol.kotlintodoapp.ui.home.viewmodel.CategoryViewModel
import com.erol.kotlintodoapp.ui.home.viewmodel.HomeViewModel
import com.erol.kotlintodoapp.ui.home.viewmodel.ShareViewModel
import com.erol.kotlintodoapp.ui.home.adapter.CategoryAdapter
import com.erol.kotlintodoapp.ui.task.adapter.TaskAdapter
import com.erol.kotlintodoapp.ui.task.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(), CategoryAdapter.CategoryListener, TaskAdapter.TaskListener {
    private lateinit var shareViewModel: ShareViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var categoryViewModel: CategoryViewModel

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        //Yeni iş Talebi girmek için
        var btnAction = view.findViewById<FloatingActionButton>(R.id.btnFloatingAction)
        btnAction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskFragment)
        }
        /*//Yeni Kategori Modal Penceresini Aç
        newCategoryButton.setOnClickListener(View.OnClickListener {
            var nCategoryDialog = CreateNewCategoryDialog()
            nCategoryDialog.show(childFragmentManager, "newCategoryModal")
        })*/
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tüm Kategorilerin seçilmesi
        context?.let { categoryViewModel.getAllCateogry(it) }

        //Kategori Adapter
        categoryAdapter = CategoryAdapter(emptyList<Category>(),this);
        categoryList = view?.findViewById(R.id.lstCategory)!!
        categoryList.adapter = categoryAdapter
        categoryList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        categoryViewModel.allCategory.observe(viewLifecycleOwner, Observer {
            categoryAdapter.setData(it)
        })

        //Tüm İşlerin Seçimi
        taskViewModel.getAllTask()

        //İşler RecyclerView
        taskAdapter = TaskAdapter(emptyList<Task>(),this);
        taskList = view?.findViewById(R.id.lstTodo)!!
        taskList.adapter = taskAdapter
        taskList.layoutManager = LinearLayoutManager(requireContext())
        taskViewModel.allTask.observe(viewLifecycleOwner, Observer { tasks ->
            taskAdapter.setData(tasks)
        })

        //SharedLiveData
        observeSharedLiveData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shareViewModel = activity?.let { ViewModelProvider(it).get(ShareViewModel::class.java) }!!
        taskViewModel = activity?.let { ViewModelProvider(it).get(TaskViewModel::class.java) }!!
        categoryViewModel =
            activity?.let { ViewModelProvider(it).get(CategoryViewModel::class.java) }!!
    }


    private fun observeSharedLiveData() {
        //Kategori ismi izlemesi
//        shareViewModel.getCategoryName().observe(viewLifecycleOwner, Observer { categoryName ->
//            categoryName?.let {
//                var nCategory = Category(0, categoryName.toString())
//                categoryViewModel.addCategory(nCategory)
//            }
//        })
    }

    override fun onCategoryItemClick(position: Int) {

        categoryViewModel.setActiveCategory(position)
        categoryAdapter.setActiveCategory(position)
        Log.d("TAG","Selected ${categoryViewModel.allCategory.value?.get(position)?.id!!}")
        taskViewModel.getTaskFromCategory(categoryViewModel.allCategory.value?.get(position)?.id!!)
        taskViewModel.allTask.observe(viewLifecycleOwner, Observer { tasks ->
            taskAdapter.setData(tasks)
        })
        //Toast.makeText(requireContext(),"Seçilen Kategori ${categoryid}",Toast.LENGTH_SHORT).show()
    }
    override fun onCompleteTaskItem(position: Int, checked: Boolean) {
        if(checked){
            var selectedId  = taskViewModel.allTask.value?.get(position)?.id!!
            Log.d("TAG"," selectedId $selectedId")
            //Toast.makeText(requireContext(),"İşlemi tamamla ${taskViewModel.allTask.value?.get(position)?.id!!}, ${checked}",Toast.LENGTH_SHORT).show()
            taskViewModel.setTaskComplete(selectedId)
            taskViewModel.getTaskFromCategory(categoryViewModel.allCategory.value?.get(categoryViewModel.getActiveCategoryid())?.id!! )
            taskViewModel.allTask.observe(viewLifecycleOwner, Observer { tasks->
                taskAdapter.setData(tasks )
            })
        }
    }
}