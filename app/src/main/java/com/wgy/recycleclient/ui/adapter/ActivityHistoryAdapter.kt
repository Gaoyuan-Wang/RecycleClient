package com.wgy.recycleclient.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CancelSign
import com.wgy.recycleclient.logic.model.CheckActivityByResidentData
import com.wgy.recycleclient.ui.activity.ActivityHistoryActivity

class ActivityHistoryAdapter(private val activity: ActivityHistoryActivity, private val activityHistoryList: ArrayList<CheckActivityByResidentData>):
    RecyclerView.Adapter<ActivityHistoryAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val activityName: TextView = view.findViewById(R.id.activityName)
        val activityPoint: TextView = view.findViewById(R.id.activityPoint)
        val activityLocation: TextView = view.findViewById(R.id.activityLocation)
        val activityBeginTime: TextView = view.findViewById(R.id.activityBeginTime)
        val activityEndTime: TextView = view.findViewById(R.id.activityEndTime)
        val cancelSign: Button = view.findViewById(R.id.cancelSign)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activityHistoryList[position]
        holder.activityName.text = activity.name
        holder.activityPoint.text = activity.point.toString()
        holder.activityLocation.text = activity.location
        holder.activityBeginTime.text = activity.startTime
        holder.activityEndTime.text = activity.endTime
        holder.cancelSign.setOnClickListener {
            this.activity.viewModel.cancelSign = CancelSign(activity.id, this.activity.viewModel.id)
            this.activity.viewModel.cancelSign(this.activity.viewModel.cancelSign)
            this.activity.viewModel.activityPosition = position
        }
    }

    override fun getItemCount(): Int = activityHistoryList.size

    //  删除数据
    fun removeData(position: Int) {
        activityHistoryList.removeAt(position)
        //删除动画
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}