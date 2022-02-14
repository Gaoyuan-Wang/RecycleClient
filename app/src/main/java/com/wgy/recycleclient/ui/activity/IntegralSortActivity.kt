package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.SortResidentByLike
import com.wgy.recycleclient.logic.viewmodel.IntegralSortViewModel
import com.wgy.recycleclient.ui.adapter.IntegralSortAdapter
import kotlinx.android.synthetic.main.integral_sort_layout.*
import kotlinx.android.synthetic.main.integral_sort_layout.back
import kotlinx.android.synthetic.main.mailing_information_layout.*

class IntegralSortActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(IntegralSortViewModel::class.java) }
    lateinit var integralSortAdapter: IntegralSortAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.integral_sort_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()

        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.sortResidentByLike = SortResidentByLike(viewModel.id)
        viewModel.sortResidentByLike(viewModel.sortResidentByLike)
        integralSortAdapter = IntegralSortAdapter(this,viewModel.allSortResidents)
        recyclerView.adapter = integralSortAdapter
        viewModel.sortResidentByLikeResponseLiveData.observe(this,{result ->
            val allSortResidentsResult = result.getOrNull()
            if(null != allSortResidentsResult){
                viewModel.allSortResidents.clear()
                viewModel.allSortResidents.addAll(allSortResidentsResult)
                integralSortAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"未能查询到排行榜", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        back.setOnClickListener{
            finish()
        }
    }
}