package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.Appointment

class HomeViewModel: ViewModel() {
    var date:String = ""
    var time:String = ""
    var address:String = ""

    var appointmentLiveData = MutableLiveData<Appointment>()
    lateinit var appointment: Appointment

    val isSuccessful = Transformations.switchMap(appointmentLiveData){
        appointment->Repository.makeAppointment(appointment.date,appointment.time,appointment.address)
    }

    fun makeAppointment(appointment: Appointment){
        appointmentLiveData.value = appointment
    }
}