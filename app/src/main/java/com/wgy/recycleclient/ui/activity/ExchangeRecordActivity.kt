package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckOnesRGR
import com.wgy.recycleclient.logic.viewmodel.ExchangeRecordViewModel
import com.wgy.recycleclient.ui.adapter.ExchangeRecordAdapter
import kotlinx.android.synthetic.main.exchange_record_layout.*

class ExchangeRecordActivity : BaseActivity() {
    val viewModel by lazy{ViewModelProvider(this).get(ExchangeRecordViewModel::class.java)}
    lateinit var exchangeRecordAdapter: ExchangeRecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exchange_record_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()

        viewModel.checkOnesRGR = CheckOnesRGR(viewModel.id)
        viewModel.checkOnesRGR(viewModel.checkOnesRGR)
        recyclerView.layoutManager = LinearLayoutManager(this)
        exchangeRecordAdapter = ExchangeRecordAdapter(this, viewModel.allExchangeRecords)
        recyclerView.adapter = exchangeRecordAdapter
        viewModel.checkOnesRGRResponseLiveData.observe(this,{result ->
            val allExchangeRecordsResult = result.getOrNull()
            if(null != allExchangeRecordsResult){
                viewModel.allExchangeRecords.clear()
                viewModel.allExchangeRecords.addAll(allExchangeRecordsResult)
                exchangeRecordAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"未能查询到任何礼物兑换记录", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.cancelGiftResponseLiveData.observe(this,{result ->
            val cancelGiftResult = result.getOrNull()
            if(null != cancelGiftResult){
                Toast.makeText(this, "成功取消兑换", Toast.LENGTH_SHORT).show()
                exchangeRecordAdapter.removeData(viewModel.giftPosition)
            }else{
                Toast.makeText(this, "取消兑换失败", Toast.LENGTH_SHORT).show()
            }
        })

        back.setOnClickListener{
            finish()
        }
    }
}