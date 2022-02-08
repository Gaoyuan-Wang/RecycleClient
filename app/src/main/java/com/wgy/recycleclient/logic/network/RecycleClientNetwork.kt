package com.wgy.recycleclient.logic.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RecycleClientNetwork {
    private val loginService = ServiceCreator.create<LoginService>()
    suspend fun getLoginData(
            username: String,
            password: String
    ) = loginService.getLoginData(username, password).await()

    private val appointmentService = ServiceCreator.create<AppointmentService>()
    suspend fun appointOrder(
            id: Int,
            rid: String,
            location: String,
            time: String,
            amount: Int,
            point: Int,
            state: Int
    ) = appointmentService.appointOrder(id, rid, location, time, amount, point, state).await()
    suspend fun checkOrder(
            id: String
    ) = appointmentService.checkOrder(id).await()
    suspend fun finishOrder(
            oid: Int,
            id: String
    ) = appointmentService.finishOrder(oid, id).await()

    private val activityService = ServiceCreator.create<ActivityService>()
    suspend fun sign(
            aid: Int,
            rid: String
    ) = activityService.sign(aid, rid).await()
    suspend fun checkAllActivity(
            rid: String
    ) = activityService.checkAllActivity(rid).await()
    suspend fun checkActivityById(
            aid: Int,
            rid: String
    ) = activityService.checkActivityById(aid, rid).await()
    suspend fun cancelSign(
            aid: Int,
            id: String
    ) = activityService.cancelSign(aid, id).await()

    private val giftService = ServiceCreator.create<GiftService>()
    suspend fun checkAllGift(
            id: String
    ) = giftService.checkAllGift(id).await()
    suspend fun checkGiftById(
            gid: Int
    ) = giftService.checkGiftById(gid).await()
    suspend fun checkGiftByKey(
            key: String
    ) = giftService.checkGiftByKey(key).await()
    suspend fun exchangeGift(
            id: Int,
            rid: String,
            gid: Int,
            contact:String,
            phone:String,
            location:String
    ) = giftService.exchangeGift(id, rid, gid, contact, phone, location).await()
    suspend fun cancelGift(
            rid: String,
            gid: Int
    ) = giftService.cancelGift(rid,gid).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>){
                    val body = response.body()
                    Log.d("Network",body.toString())
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