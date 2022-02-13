package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckActivityByCategoryData
import com.wgy.recycleclient.logic.model.Sign
import com.wgy.recycleclient.ui.activity.ActivityCategoryActivity

class CategoryAdapter (private val activity: ActivityCategoryActivity, private val activityList: ArrayList<CheckActivityByCategoryData>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val activityName: TextView = view.findViewById(R.id.activityName)
        val activityPoint: TextView = view.findViewById(R.id.activityPoint)
        val activityLocation: TextView = view.findViewById(R.id.activityLocation)
        val activityBeginTime: TextView = view.findViewById(R.id.activityBeginTime)
        val activityEndTime: TextView = view.findViewById(R.id.activityEndTime)
        val sign: Button = view.findViewById(R.id.sign)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activityList[position]
        holder.activityName.text = activity.name
        holder.activityPoint.text = activity.point.toString()
        holder.activityLocation.text = activity.location
        holder.activityBeginTime.text = activity.startTime
        holder.activityEndTime.text = activity.endTime
        holder.sign.setOnClickListener {
            this.activity.viewModel.sign = Sign(activity.id,this.activity.viewModel.id)
            this.activity.viewModel.sign(this.activity.viewModel.sign)
            this.activity.viewModel.activityPosition = position
            }
    }

    override fun getItemCount(): Int = activityList.size

    //  删除数据
    fun removeData(position: Int) {
        activityList.removeAt(position)
        //删除动画
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}