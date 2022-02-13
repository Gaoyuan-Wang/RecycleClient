package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckActivityByCategory
import com.wgy.recycleclient.logic.viewmodel.ActivityCategoryViewModel
import com.wgy.recycleclient.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.activity_category_layout.*

class ActivityCategoryActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(ActivityCategoryViewModel::class.java)}
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()
        viewModel.category = intent.getStringExtra("category").toString()
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.checkActivityByCategory = CheckActivityByCategory(viewModel.category,viewModel.id)
        viewModel.checkActivityByCategory(viewModel.checkActivityByCategory)
        adapter = CategoryAdapter(this,viewModel.allActivities)
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

        viewModel.signResponseLiveData.observe(this, {result->
            val signResult = result.getOrNull()
            if(null != signResult){
                Toast.makeText(this, "成功报名活动", Toast.LENGTH_SHORT).show()
                adapter.removeData(viewModel.activityPosition)
            }else{
                Toast.makeText(this, "报名活动失败", Toast.LENGTH_SHORT).show()
            }
        })

        back.setOnClickListener {
            finish()
        }
    }
}