package com.wgy.recycleclient.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.viewmodel.ActivityViewModel
import com.wgy.recycleclient.ui.adapter.ActivityAdapter
import kotlinx.android.synthetic.main.fragment_activity.*

class ActivityFragment:Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(ActivityViewModel::class.java)}
    private lateinit var adapter: ActivityAdapter

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
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.checkAllActivity(viewModel.checkAllActivity)
        adapter = ActivityAdapter(this,viewModel.allActivity)
        recyclerView.adapter = adapter
        viewModel.activities.observe(this, Observer{result ->
            val activities = result.getOrNull()
            if(null != activities){
                recyclerView.visibility = View.VISIBLE
                viewModel.allActivity.clear()
                viewModel.allActivity.addAll(activities)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到任何活动",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}