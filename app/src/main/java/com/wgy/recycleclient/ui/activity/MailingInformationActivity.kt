package com.wgy.recycleclient.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.viewmodel.MailingInformationViewModel
import kotlinx.android.synthetic.main.mailing_information_layout.*

class MailingInformationActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(MailingInformationViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mailing_information_layout)

        confirm.setOnClickListener{
            viewModel.contact = contact.text.toString()
            viewModel.location = location.text.toString()
            viewModel.phone = phone.text.toString()
            val editor = getSharedPreferences("MailingInformation", MODE_PRIVATE).edit()
            editor.putString("isSaved","true")
            editor.putString("contact",viewModel.contact)
            editor.putString("location",viewModel.location)
            editor.putString("phone",viewModel.phone)
            editor.apply()

            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show()
        }

        back.setOnClickListener{
            finish()
        }
    }

}