package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.AllActivity

class ActivityAdapter(private val fragment: Fragment, private val activityList: List<AllActivity>):
    RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val activityName: TextView = view.findViewById(R.id.activityName)
        val activityPoint: TextView = view.findViewById(R.id.activityPoint)
        val activityLocation: TextView = view.findViewById(R.id.activityLocation)
        val activityBeginTime: TextView = view.findViewById(R.id.activityBeginTime)
        val activityEndTime: TextView = view.findViewById(R.id.activityEndTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activityList[position]
        holder.activityName.text = activity.name
        holder.activityPoint.text = activity.point.toString()
        holder.activityLocation.text = activity.location
        holder.activityBeginTime.text = activity.startTime
        holder.activityEndTime.text = activity.endTime
    }

    override fun getItemCount(): Int = activityList.size
}