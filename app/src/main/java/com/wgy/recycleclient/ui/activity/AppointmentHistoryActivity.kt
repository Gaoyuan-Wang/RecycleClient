package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.viewmodel.AppointmentHistoryViewModel
import com.wgy.recycleclient.ui.adapter.AppointmentAdapter
import kotlinx.android.synthetic.main.fragment_activity.*

class AppointmentHistoryActivity : BaseActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(AppointmentHistoryViewModel::class.java)}
    private lateinit var adapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_history_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()
        recyclerView.layoutManager = LinearLayoutManager(this)
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
    }

}