package com.wgy.recycleclient.logic

import androidx.lifecycle.liveData
import com.wgy.recycleclient.logic.model.Appointment
import com.wgy.recycleclient.logic.model.AppointmentResponse
import com.wgy.recycleclient.logic.model.Login
import com.wgy.recycleclient.logic.network.RecycleClientNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    //获取登录数据返回给LoginViewModel
    fun getLoginData(username: String,password: String) = liveData(Dispatchers.IO){
        val result = try {
            val loginResponse = RecycleClientNetwork.getLoginData(username, password)
            if (loginResponse.isAccessible){
                Result.success(loginResponse.isAccessible)
            }else{
                Result.failure(RuntimeException("Response accessibility is ${loginResponse.isAccessible}"))
            }
        }catch (e:Exception){
            Result.failure<Login>(e)
        }
        emit(result)
    }

    //提交预定数据返回给HomeViewModel
    fun makeAppointment(date: String,time: String,address: String) = liveData(Dispatchers.IO){
        val result = try {
            val appointmentResponse = RecycleClientNetwork.makeAppointment(date, time, address)
            if (appointmentResponse.isSuccessful){
                Result.success(appointmentResponse.isSuccessful)
            }else{
                Result.failure(RuntimeException("Response success is ${appointmentResponse.isSuccessful}"))
            }
        }catch (e:Exception){
            Result.failure<Appointment>(e)
        }
        emit(result)
    }
}