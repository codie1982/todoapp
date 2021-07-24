package com.erol.kotlintodoapp.ui.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erol.kotlintodoapp.R
import com.erol.kotlintodoapp.data.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(var categoryList: List<Category>,
                      var categoryListener: CategoryListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    lateinit var context: Context
     var selectedCategory:Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view, categoryListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryName.text = categoryList[position].category_name
        if(selectedCategory.equals(position)){
            holder.itemView.background = context.getDrawable(R.drawable.ic_launcher_background)
        }else {
            holder.itemView.background = context.getDrawable(R.drawable.round_rect_shape)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categoryList: List<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun setActiveCategory(categoryid: Int) {
      this.selectedCategory = categoryid
        notifyDataSetChanged()
    }

    class CategoryViewHolder(item: View, categoryListener: CategoryListener) :
        RecyclerView.ViewHolder(item), View.OnClickListener {
        var categoryName: TextView
        var categoryListener: CategoryListener

        init {
            categoryName = item.txtCategoryName
            item.setOnClickListener(this)
            this.categoryListener = categoryListener
        }

        override fun onClick(v: View) {
            categoryListener.onCategoryItemClick(adapterPosition)
        }
    }

    interface CategoryListener {
        fun onCategoryItemClick(categoryid: Int)
    }
}