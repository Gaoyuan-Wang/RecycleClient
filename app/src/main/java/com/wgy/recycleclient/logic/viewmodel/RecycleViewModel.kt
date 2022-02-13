package com.wgy.recycleclient.logic.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.RecycleClientApplication.Companion.context
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.*

class RecycleViewModel: ViewModel() {
    var date = ""
    var time = ""
    var location:String = ""
    var amount:Int = 0
    //获取当前登录的用户ID
    var id: String = ""

    var appointmentLiveData = MutableLiveData<Appointment>()
    lateinit var appointment: Appointment

    val appointIsSuccessful = Transformations.switchMap(appointmentLiveData){ appointment->
        Repository.appointOrder(
            appointment.checkId,
            appointment.rid,
            appointment.location,
            appointment.time,
            appointment.amount,
            appointment.point,
        )
    }
    fun appointOrder(appointment: Appointment){
        appointmentLiveData.value = appointment
    }
}