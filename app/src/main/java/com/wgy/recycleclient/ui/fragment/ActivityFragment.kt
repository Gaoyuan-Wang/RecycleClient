package com.wgy.recycleclient.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckActivityById
import com.wgy.recycleclient.logic.model.CheckRecommendActivity
import com.wgy.recycleclient.logic.viewmodel.ActivityViewModel
import com.wgy.recycleclient.ui.activity.ActivityHistoryActivity
import com.wgy.recycleclient.ui.adapter.ActivityCategoryAdapter
import com.wgy.recycleclient.ui.adapter.ActivityIdAdapter
import com.wgy.recycleclient.ui.adapter.ActivityRecommendAdapter
import kotlinx.android.synthetic.main.fragment_activity.*


class ActivityFragment:Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(ActivityViewModel::class.java)}
    private lateinit var activityCategoryAdapter: ActivityCategoryAdapter
    private lateinit var activityRecommendAdapter: ActivityRecommendAdapter
    private lateinit var activityIdAdapter: ActivityIdAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.id = activity?.getSharedPreferences(
            "RegisterAccount",
            AppCompatActivity.MODE_PRIVATE
        )?.getString("id", null).toString()
        categoryRecyclerView.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL,
            false
        )
        activityCategoryAdapter = ActivityCategoryAdapter(this, viewModel.activityCategoryList)
        categoryRecyclerView.adapter = activityCategoryAdapter

        recommendRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        viewModel.checkRecommendActivity = CheckRecommendActivity(viewModel.id)
        viewModel.checkRecommendActivity(viewModel.checkRecommendActivity)
        activityRecommendAdapter = ActivityRecommendAdapter(this, viewModel.allRecommendActivities)
        recommendRecyclerView.adapter = activityRecommendAdapter
        viewModel.checkRecommendActivityResponseLiveData.observe(this, { result ->
            val recommendActivityResult = result.getOrNull()
            if (null != recommendActivityResult) {
                viewModel.allRecommendActivities.clear()
                viewModel.allRecommendActivities.addAll(recommendActivityResult)
                activityRecommendAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何推荐活动", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.signResponseLiveData.observe(this, { result ->
            val signResult = result.getOrNull()
            if (null != signResult) {
                Toast.makeText(activity, "成功报名活动", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "报名活动失败", Toast.LENGTH_SHORT).show()
            }
        })

        residentActivity.setOnClickListener {
            val intent = Intent(activity, ActivityHistoryActivity::class.java)
            startActivity(intent)
        }

        searchActivity.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.checkActivityById = CheckActivityById(Integer.parseInt(searchActivity.text.toString()),viewModel.id)
                viewModel.checkActivityById(viewModel.checkActivityById)
                return@OnEditorActionListener true
            }
            false
        })

        idRecyclerView.layoutManager = LinearLayoutManager(activity)
        activityIdAdapter = ActivityIdAdapter(this,viewModel.IdActivity)
        idRecyclerView.adapter = activityIdAdapter
        viewModel.checkActivityByIdResponseLiveData.observe(this,{result->
            val idActivityResult = result.getOrNull()
            if(null != idActivityResult){
                idRecyclerView.visibility = View.VISIBLE
                viewModel.IdActivity.clear()
                viewModel.IdActivity.addAll(listOf(idActivityResult))
                activityIdAdapter.notifyDataSetChanged()
            }else {
                Toast.makeText(activity, "未能查询到任何活动", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

//        viewModel.checkAllActivity = CheckAllActivity(viewModel.id)
//        viewModel.checkAllActivity(viewModel.checkAllActivity)
//        adapter = ActivityRecommendAdapter(this,viewModel.allActivity)
//        recyclerView.adapter = adapter
//        viewModel.activities.observe(this, Observer{result ->
//            val activities = result.getOrNull()
//            if(null != activities){
//                recyclerView.visibility = View.VISIBLE
//                viewModel.allActivity.clear()
//                viewModel.allActivity.addAll(activities)
//                adapter.notifyDataSetChanged()
//            }else{
//                Toast.makeText(activity,"未能查询到任何活动",Toast.LENGTH_SHORT).show()
//                result.exceptionOrNull()?.printStackTrace()
//            }
//        })
    }
}