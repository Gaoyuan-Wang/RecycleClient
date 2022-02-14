package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.SortResidentByLikeData
import com.wgy.recycleclient.ui.activity.IntegralSortActivity

class IntegralSortAdapter (private val activity: IntegralSortActivity, private val sortList: List<SortResidentByLikeData>):
    RecyclerView.Adapter<IntegralSortAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val userName: TextView = view.findViewById(R.id.userName)
        val userPoint: TextView = view.findViewById(R.id.userPoint)
        val userLike: TextView = view.findViewById(R.id.userLike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sort_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sortList = sortList[position]
        holder.userName.text = sortList.username
        holder.userPoint.text = sortList.point.toString()
        holder.userLike.text = sortList.like.toString()
    }

    override fun getItemCount(): Int = sortList.size
}