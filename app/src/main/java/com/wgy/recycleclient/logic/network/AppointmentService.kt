package com.wgy.recycleclient.logic.network

import com.wgy.recycleclient.logic.model.AppointmentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface AppointmentService {
    /**
     * @param date    预定日期
     * @param time    预定时间
     * @param address 地址
     */
    @GET("Appointment.json")
    fun makeAppointment(
        @Query("date") date: String,
        @Query("time") time: String,
        @Query("address")address: String
    ): Call<AppointmentResponse>
}