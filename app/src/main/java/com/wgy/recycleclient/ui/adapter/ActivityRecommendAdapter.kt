package com.wgy.recycleclient.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckRecommendActivityData
import com.wgy.recycleclient.logic.model.Sign
import com.wgy.recycleclient.ui.fragment.ActivityFragment

class ActivityRecommendAdapter(private val fragment: ActivityFragment, private val activityList: ArrayList<CheckRecommendActivityData>):
    RecyclerView.Adapter<ActivityRecommendAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val activityName: TextView = view.findViewById(R.id.activityName)
        val activityPoint: TextView = view.findViewById(R.id.activityPoint)
        val activityLocation: TextView = view.findViewById(R.id.activityLocation)
        val activityBeginTime: TextView = view.findViewById(R.id.activityBeginTime)
        val activityEndTime: TextView = view.findViewById(R.id.activityEndTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_recommend,parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            AlertDialog.Builder(parent.context).apply {
                setCancelable(false)
                setMessage("您确定报名此活动吗？：\n${activityList[position + 1].name}\n")
                setPositiveButton("确 定"){ _, _->
                    fragment.viewModel.sign = Sign(activityList[position + 1].id,fragment.viewModel.id)
                    fragment.viewModel.sign(fragment.viewModel.sign)
                }
                setNegativeButton("取 消"){ _, _-> }
                show()
            }
        }
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