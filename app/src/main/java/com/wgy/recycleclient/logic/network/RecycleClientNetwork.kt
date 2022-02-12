package com.wgy.recycleclient.logic.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RecycleClientNetwork {
    private val residentService = ServiceCreator.create<ResidentService>()
    suspend fun getLoginData(
            username: String,
            password: String
    ) = residentService.getLoginData(username, password).await()
    suspend fun checkPRById(
            id: String
    ) = residentService.checkPRById(id).await()
    suspend fun checkResidentById(
            id: String
    ) = residentService.checkResidentById(id).await()
    suspend fun sortResidentByLike(
            id: String
    ) = residentService.sortResidentByLike(id).await()

    private val appointmentService = ServiceCreator.create<AppointmentService>()
    suspend fun appointOrder(
            checkId: String,
            rid: String,
            location: String,
            time: String,
            amount: Int,
            point: Int,
    ) = appointmentService.appointOrder(checkId, rid, location, time, amount, point).await()
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
    suspend fun checkActivityByCategory(
            category: String,
            rid: String
    ) = activityService.checkActivityByCategory(category, rid).await()
    suspend fun checkActivityByResident(
            rid: String
    ) = activityService.checkActivityByResident(rid).await()
    suspend fun checkRecommendActivity(
            rid: String
    ) = activityService.checkRecommendActivity(rid).await()
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
            id: String,
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