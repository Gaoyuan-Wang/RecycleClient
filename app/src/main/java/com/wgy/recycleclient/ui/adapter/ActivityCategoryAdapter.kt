package com.wgy.recycleclient.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.ui.activity.ActivityCategoryActivity
import com.wgy.recycleclient.ui.fragment.ActivityFragment

class ActivityCategoryAdapter(private val fragment: ActivityFragment, private val activityCategoryList: List<String>):
        RecyclerView.Adapter<ActivityCategoryAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val categoryName: TextView = view.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_category,parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val intent = Intent(parent.context, ActivityCategoryActivity::class.java).apply {
                putExtra("category",activityCategoryList[position])
            }
            fragment.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activityCategory = activityCategoryList[position]
        holder.categoryName.text = activityCategory
    }

    override fun getItemCount(): Int = activityCategoryList.size
}