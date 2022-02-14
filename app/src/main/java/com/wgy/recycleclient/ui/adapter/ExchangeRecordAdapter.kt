package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CancelGift
import com.wgy.recycleclient.logic.model.CheckOnesRGRData
import com.wgy.recycleclient.ui.activity.ExchangeRecordActivity

class ExchangeRecordAdapter (private val activity: ExchangeRecordActivity, private val giftList: ArrayList<CheckOnesRGRData>):
    RecyclerView.Adapter<ExchangeRecordAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val giftId: TextView = view.findViewById(R.id.giftId)
        val contact: TextView = view.findViewById(R.id.contact)
        val cancelExchange: Button = view.findViewById(R.id.cancelExchange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gift = giftList[position]
        holder.giftId.text = gift.gid.toString()
        holder.contact.text = gift.contact
        holder.cancelExchange.setOnClickListener {
            activity.viewModel.cancelGift = CancelGift(activity.viewModel.id,gift.id)
            activity.viewModel.cancelGift(activity.viewModel.cancelGift)
            activity.viewModel.giftPosition = position
        }
    }

    override fun getItemCount(): Int = giftList.size

    //  删除数据
    fun removeData(position: Int) {
        giftList.removeAt(position)
        //删除动画
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}