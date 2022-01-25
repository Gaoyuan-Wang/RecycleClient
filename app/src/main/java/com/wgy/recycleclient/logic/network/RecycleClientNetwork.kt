package com.wgy.recycleclient.logic.network


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RecycleClientNetwork {
    private val loginService = ServiceCreator.create<LoginService>()

    suspend fun getLoginData(username: String,password: String) = loginService.getLoginData(username,password).await()

    private val appointmentService = ServiceCreator.create<AppointmentService>()

    suspend fun makeAppointment(date: String,time: String,address: String) = appointmentService.makeAppointment(date,time,address).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>){
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                            RuntimeException("Response body is null!"))
                }

                override fun onFailure(call: Call<T>, t: Throwable){
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}