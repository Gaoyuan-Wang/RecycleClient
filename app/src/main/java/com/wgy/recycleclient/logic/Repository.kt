package com.wgy.recycleclient.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.wgy.recycleclient.logic.network.RecycleClientNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    //获取登录数据返回给LoginViewModel
    fun getLoginData(username: String,password: String) = fire(Dispatchers.IO){
        val loginResponse = RecycleClientNetwork.getLoginData(username, password)
        //Log.d("Repository", loginResponse.isAccessible.toString())
        if ("true" == loginResponse.success){
            Result.success(loginResponse.data)
        }else{
            Result.failure(RuntimeException("Response accessibility is ${loginResponse.success}"))
        }
    }

    fun checkPRById(id: String) = fire(Dispatchers.IO){
        val checkPRByIdResponse = RecycleClientNetwork.checkPRById(id)
        if ("true" == checkPRByIdResponse.success){
            Result.success(checkPRByIdResponse.data)
        }else{
            Result.failure(RuntimeException("Response accessibility is ${checkPRByIdResponse.success}"))
        }
    }

    fun checkResidentById(id: String) = fire(Dispatchers.IO){
        val checkResidentByIdResponse = RecycleClientNetwork.checkResidentById(id)
        if ("true" == checkResidentByIdResponse.success){
            Result.success(checkResidentByIdResponse.data)
        }else{
            Result.failure(RuntimeException("Response accessibility is ${checkResidentByIdResponse.success}"))
        }
    }

    fun sortResidentByLike(id: String) = fire(Dispatchers.IO){
        val sortResidentByLikeResponse = RecycleClientNetwork.sortResidentByLike(id)
        if ("true" == sortResidentByLikeResponse.success){
            Result.success(sortResidentByLikeResponse.data)
        }else{
            Result.failure(RuntimeException("Response accessibility is ${sortResidentByLikeResponse.success}"))
        }
    }

    //提交预定数据返回给HomeViewModel
    fun appointOrder(checkId: String, rid: String, location: String, time: String, amount: Int, point: Int) = fire(Dispatchers.IO){
        val appointmentResponse = RecycleClientNetwork.appointOrder(checkId, rid, location, time, amount, point)
        if ("true" == appointmentResponse.success){
            Result.success(appointmentResponse.success)
        }else{
            Result.failure(RuntimeException("Response code is ${appointmentResponse.success}"))
        }
    }

    fun checkOrder(id: String) = fire(Dispatchers.IO){
        val checkOrderResponse = RecycleClientNetwork.checkOrder(id)
        if ("true" == checkOrderResponse.success){
            Result.success(checkOrderResponse.data)
        }else{
            Result.failure(RuntimeException("Response code is ${checkOrderResponse.success}"))
        }
    }

    fun finishOrder(oid: Int, id: String) = fire(Dispatchers.IO){
        val finishOrderResponse = RecycleClientNetwork.finishOrder(oid, id)
        if ("true" == finishOrderResponse.success){
            Result.success(finishOrderResponse.success)
        }else{
            Result.failure(RuntimeException("Response code is ${finishOrderResponse.success}"))
        }
    }

    fun sign(aid: Int, rid: String) = fire(Dispatchers.IO){
        val activityResponse = RecycleClientNetwork.sign(aid, rid)
        if ("true" == activityResponse.success){
            Result.success(activityResponse.success)
        }else {
            Result.failure(RuntimeException("Response code is ${activityResponse.success}"))
        }
    }

    fun checkAllActivity(rid: String) = fire(Dispatchers.IO){
        val checkAllActivityResponse = RecycleClientNetwork.checkAllActivity(rid)
        if ("true" == checkAllActivityResponse.success){
            Result.success(checkAllActivityResponse.data)
        }else {
            Result.failure(RuntimeException("Response code is ${checkAllActivityResponse.success}"))
        }
    }

    fun checkActivityById(aid: Int,rid: String) = fire(Dispatchers.IO){
        val checkActivityByIdResponse = RecycleClientNetwork.checkActivityById(aid, rid)
        if ("true" == checkActivityByIdResponse.success){
            Result.success(checkActivityByIdResponse.data)
        }else {
            Result.failure(RuntimeException("Response code is ${checkActivityByIdResponse.success}"))
        }
    }

    fun checkActivityByCategory(category: String, rid: String) = fire(Dispatchers.IO){
        val checkActivityByCategoryResponse = RecycleClientNetwork.checkActivityByCategory(category, rid)
        if ("true" == checkActivityByCategoryResponse.success){
            Result.success(checkActivityByCategoryResponse.data)
        }else{
            Result.failure(RuntimeException("Response code is ${checkActivityByCategoryResponse.success}"))
        }
    }

    fun checkActivityByResident(rid: String) = fire(Dispatchers.IO){
        val checkActivityByResidentResponse = RecycleClientNetwork.checkActivityByResident(rid)
        if ("true" == checkActivityByResidentResponse.success){
            Result.success(checkActivityByResidentResponse.data)
        }else{
            Result.failure(RuntimeException("Response code is ${checkActivityByResidentResponse.success}"))
        }
    }

    fun checkRecommendActivity(rid: String) = fire(Dispatchers.IO){
        val checkRecommendActivityResponse = RecycleClientNetwork.checkRecommendActivity(rid)
        if ("true" == checkRecommendActivityResponse.success){
            Result.success(checkRecommendActivityResponse.data)
        }else{
            Result.failure(RuntimeException("Response code is ${checkRecommendActivityResponse.success}"))
        }
    }

    fun cancelSign(aid: Int, id: String) = fire(Dispatchers.IO){
        val cancelSignResponse = RecycleClientNetwork.cancelSign(aid, id)
        if ("true" == cancelSignResponse.success){
            Result.success(cancelSignResponse.success)
        }else {
            Result.failure(RuntimeException("Response code is ${cancelSignResponse.success}"))
        }
    }

    fun checkAllGift(rid: String) = fire(Dispatchers.IO){
        val checkAllGiftResponse = RecycleClientNetwork.checkAllGift(rid)
        if ("true" == checkAllGiftResponse.success){
            Result.success(checkAllGiftResponse.data)
        }else {
            Result.failure(RuntimeException("Response code is ${checkAllGiftResponse.success}"))
        }
    }

    fun checkGiftById(gid: Int) = fire(Dispatchers.IO){
        val checkGiftByIdResponse = RecycleClientNetwork.checkGiftById(gid)
        if ("true" == checkGiftByIdResponse.success){
            Result.success(checkGiftByIdResponse.data)
        }else {
            Result.failure(RuntimeException("Response code is ${checkGiftByIdResponse.success}"))
        }
    }

    fun checkGiftByKey(key: String) = fire(Dispatchers.IO){
        val checkGiftByKeyResponse = RecycleClientNetwork.checkGiftByKey(key)
        if ("true" == checkGiftByKeyResponse.success){
            Result.success(checkGiftByKeyResponse.data)
        }else {
            Result.failure(RuntimeException("Response code is ${checkGiftByKeyResponse.success}"))
        }
    }

    fun checkOnesRGR(id: String) = fire(Dispatchers.IO){
        val checkOnesRGRResponse = RecycleClientNetwork.checkOnesRGR(id)
        if ("true" == checkOnesRGRResponse.success){
            Result.success(checkOnesRGRResponse.data)
        }else {
            Result.failure(RuntimeException("Response code is ${checkOnesRGRResponse.success}"))
        }
    }

    fun exchangeGift(id: String, rid: String, gid: Int, contact:String, phone:String, location:String) = fire(Dispatchers.IO){
        val exchangeGiftResponse = RecycleClientNetwork.exchangeGift(id, rid, gid, contact, phone, location)
        if("true" == exchangeGiftResponse.success){
            Result.success(exchangeGiftResponse.success)
        }else {
            Result.failure(RuntimeException("Response code is ${exchangeGiftResponse.success}"))}
    }

    fun cancelGift(id: String, gid: Int) = fire(Dispatchers.IO){
        val cancelGiftResponse = RecycleClientNetwork.cancelGift(id, gid)
        if("true" == cancelGiftResponse.success){
            Result.success(cancelGiftResponse.success)
        }else {
            Result.failure(RuntimeException("Response code is ${cancelGiftResponse.success}"))}
    }

    private fun <T> fire(context: CoroutineContext, block:suspend()->Result<T>)=
            liveData(context){
                val result = try{
                    block()
                }catch (e:Exception){
                    Result.failure(e)
                }
                emit(result)
            }
}