package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckOrder
import com.wgy.recycleclient.logic.viewmodel.AppointmentHistoryViewModel
import com.wgy.recycleclient.ui.adapter.AppointmentAdapter
import kotlinx.android.synthetic.main.appointment_history_layout.*

class AppointmentHistoryActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(AppointmentHistoryViewModel::class.java)}
    private lateinit var adapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_history_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.checkOrder = CheckOrder(viewModel.id)
        viewModel.checkOrder(viewModel.checkOrder)
        adapter = AppointmentAdapter(this,viewModel.allOrders)
        recyclerView.adapter = adapter
        viewModel.orders.observe(this, {result ->
            val orders = result.getOrNull()
            if(null != orders){
                recyclerView.visibility = View.VISIBLE
                viewModel.allOrders.clear()
                viewModel.allOrders.addAll(orders)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"未能查询到任何历史订单", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.finishOrderIsSuccessful.observe(this,{result ->
            val finishResult = result.getOrNull()
            if(null != finishResult){
                Toast.makeText(this, "成功取消订单", Toast.LENGTH_SHORT).show()
                adapter.removeData(viewModel.appointmentPosition)
            }else{
                Toast.makeText(this, "取消订单失败", Toast.LENGTH_SHORT).show()
            }
        })

        back.setOnClickListener{
            finish()
        }
    }

}