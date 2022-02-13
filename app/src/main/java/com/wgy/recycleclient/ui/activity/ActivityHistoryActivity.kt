package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckActivityByResident
import com.wgy.recycleclient.logic.viewmodel.ActivityHistoryViewModel
import com.wgy.recycleclient.ui.adapter.ActivityHistoryAdapter
import kotlinx.android.synthetic.main.activity_category_layout.*

class ActivityHistoryActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(ActivityHistoryViewModel::class.java)}
    private lateinit var adapter: ActivityHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.checkActivityByResident = CheckActivityByResident(viewModel.id)
        viewModel.checkActivityByResident(viewModel.checkActivityByResident)
        adapter = ActivityHistoryAdapter(this, viewModel.allActivities)
        recyclerView.adapter = adapter
        viewModel.activityLiveData.observe(this, {result ->
            val activities = result.getOrNull()
            if(null != activities){
                recyclerView.visibility = View.VISIBLE
                viewModel.allActivities.clear()
                viewModel.allActivities.addAll(activities)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"未能查询到任何历史订单", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.cancelSignResponseLiveData.observe(this, {result->
            val cancelSignResult = result.getOrNull()
            if(null != cancelSignResult){
                Toast.makeText(this, "成功取消报名", Toast.LENGTH_SHORT).show()
                adapter.removeData(viewModel.activityPosition)
            }else{
                Toast.makeText(this, "取消报名活动失败", Toast.LENGTH_SHORT).show()
            }
        })

        back.setOnClickListener {
            finish()
        }
    }
}