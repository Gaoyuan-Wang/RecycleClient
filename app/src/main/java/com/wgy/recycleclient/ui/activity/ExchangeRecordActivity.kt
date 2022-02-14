package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import kotlinx.android.synthetic.main.integral_sort_layout.*

class ExchangeRecordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exchange_record_layout)

        back.setOnClickListener{
            finish()
        }
    }
}