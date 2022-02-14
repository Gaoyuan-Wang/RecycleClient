package com.wgy.recycleclient.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckResidentById
import com.wgy.recycleclient.logic.viewmodel.PersonalInformationViewModel
import kotlinx.android.synthetic.main.personal_information_layout.*

class PersonalInformationActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(PersonalInformationViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_information_layout)
        viewModel.id = this.getSharedPreferences(
            "RegisterAccount",
            MODE_PRIVATE
        )?.getString("id", null).toString()

        viewModel.checkResidentById = CheckResidentById(viewModel.id)
        viewModel.checkResidentById(viewModel.checkResidentById)
        viewModel.checkResidentByIdResponseLiveData.observe(this,{result ->
            val personalInformationResult = result.getOrNull()
            if(null != personalInformationResult){
                username.text = personalInformationResult.username
                password.text = personalInformationResult.password
                phone.text = personalInformationResult.phone
                integral.text = personalInformationResult.point.toString()
                like.text = personalInformationResult.like.toString()
            }else{
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        back.setOnClickListener {
            finish()
        }
    }
}