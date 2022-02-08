package com.wgy.recycleclient.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.Order
import com.wgy.recycleclient.logic.viewmodel.AppointmentHistoryViewModel

class AppointmentAdapter (private val activity: Activity, private val appointmentList: List<Order>):
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appointmentState: TextView = view.findViewById(R.id.appointmentState)
        val cancel: Button = view.findViewById(R.id.cancel)
        val appointmentLocation: TextView = view.findViewById(R.id.appointmentLocation)
        val appointmentAmount: TextView = view.findViewById(R.id.appointmentAmount)
        val appointmentTime: TextView = view.findViewById(R.id.appointmentTime)
        val appointmentPoint: TextView = view.findViewById(R.id.appointmentPoint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.appointmentState.text = appointment.state.toString()
        holder.appointmentLocation.text = appointment.location
        holder.appointmentAmount.text = appointment.amount.toString()
        holder.appointmentTime.text = appointment.time
        holder.appointmentPoint.text = appointment.point.toString()
        holder.cancel.setOnClickListener {
            }
    }

    override fun getItemCount(): Int = appointmentList.size
}