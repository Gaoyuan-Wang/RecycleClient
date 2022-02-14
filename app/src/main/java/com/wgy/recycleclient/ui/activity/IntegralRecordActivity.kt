package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckPRById
import com.wgy.recycleclient.logic.viewmodel.IntegralRecordViewModel
import com.wgy.recycleclient.ui.adapter.IntegralRecordAdapter
import kotlinx.android.synthetic.main.integral_record_layout.*

class IntegralRecordActivity : BaseActivity() {
    val viewModel by lazy {ViewModelProvider(this).get(IntegralRecordViewModel::class.java)}
    lateinit var integralRecordAdapter: IntegralRecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.integral_record_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()

        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.checkPRById = CheckPRById(viewModel.id)
        viewModel.checkPRById(viewModel.checkPRById)
        integralRecordAdapter = IntegralRecordAdapter(this,viewModel.allRecord)
        recyclerView.adapter = integralRecordAdapter
        viewModel.checkPRByIdResponseLiveData.observe(this,{result ->
            val allRecordsResult = result.getOrNull()
            if(null != allRecordsResult){
                viewModel.allRecord.clear()
                viewModel.allRecord.addAll(allRecordsResult)
                integralRecordAdapter.notifyDataSetChanged()
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