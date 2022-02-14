package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckPRByIdData
import com.wgy.recycleclient.ui.activity.IntegralRecordActivity

class IntegralRecordAdapter (private val activity: IntegralRecordActivity, private val recordList: List<CheckPRByIdData>):
    RecyclerView.Adapter<IntegralRecordAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val type: TextView = view.findViewById(R.id.type)
        val point: TextView = view.findViewById(R.id.point)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.integral_record_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = recordList[position]
        if(1 == record.typeId){
            holder.type.text = "增加"
        }else if(0 == record.typeId){
            holder.type.text = "扣除"
        }
        holder.point.text = record.point.toString()
    }

    override fun getItemCount(): Int = recordList.size
}