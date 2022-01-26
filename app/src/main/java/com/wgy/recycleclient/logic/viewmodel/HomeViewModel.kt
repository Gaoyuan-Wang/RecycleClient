package com.wgy.recycleclient.logic.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.RecycleClientApplication.Companion.context
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.Appointment


class HomeViewModel: ViewModel() {
    var date:String = ""
    var time:String = ""
    var address:String = ""
    //获取当前登录的用户名
    var userName: String = context.getSharedPreferences(
            "RegisterAccount",
            AppCompatActivity.MODE_PRIVATE
    ).getString("UserName", null).toString()

    var appointmentLiveData = MutableLiveData<Appointment>()
    lateinit var appointment: Appointment

    val isSuccessful = Transformations.switchMap(appointmentLiveData){ appointment->
        Repository.makeAppointment(appointment.username,appointment.date, appointment.time, appointment.address)
    }

    fun makeAppointment(appointment: Appointment){
        appointmentLiveData.value = appointment
    }
}