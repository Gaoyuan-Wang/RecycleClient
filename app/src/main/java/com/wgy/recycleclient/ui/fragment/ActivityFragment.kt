package com.wgy.recycleclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.viewmodel.HomeViewModel
import com.wgy.recycleclient.ui.adapter.ActivityAdapter
import kotlinx.android.synthetic.main.fragment_activity.*

class ActivityFragment:Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java)}
    private lateinit var adapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.checkOrder(viewModel.checkOrder)
        adapter = ActivityAdapter(this,viewModel.allActivity)
        recyclerView.adapter = adapter
        viewModel.activities.observe(viewLifecycleOwner, Observer{result ->
            val activities = result.getOrNull()
            if(null != activities){
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