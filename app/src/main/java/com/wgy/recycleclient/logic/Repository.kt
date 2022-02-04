package com.wgy.recycleclient.logic

import androidx.lifecycle.liveData
import com.wgy.recycleclient.logic.network.RecycleClientNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    //获取登录数据返回给LoginViewModel
    fun getLoginData(username: String,password: String) = fire(Dispatchers.IO){
        val loginResponse = RecycleClientNetwork.getLoginData(username, password)
        if (1 == loginResponse.isAccessible){
            Result.success(loginResponse.id)
        }else{
            Result.failure(RuntimeException("Response accessibility is ${loginResponse.isAccessible}"))
        }
    }

    //提交预定数据返回给HomeViewModel
    fun appointOrder(id: Int, rid: String, location: String, time: String, amount: Int, point: Int, state: Int) = fire(Dispatchers.IO){
        val appointmentResponse = RecycleClientNetwork.appointOrder(id, rid, location, time, amount, point, state)
        if (200 == appointmentResponse.isSuccessful){
            Result.success(appointmentResponse.isSuccessful)
        }else{
            Result.failure(RuntimeException("Response code is ${appointmentResponse.isSuccessful}"))
        }
    }

    fun checkOrder(id: String) = fire(Dispatchers.IO){
        val checkOrderResponse = RecycleClientNetwork.checkOrder(id)
        if (1 == checkOrderResponse.status){
            Result.success(checkOrderResponse.orders)
        }else{
            Result.failure(RuntimeException("Response code is ${checkOrderResponse.status}"))
        }
    }

    fun finishOrder(oid: Int, id: String) = fire(Dispatchers.IO){
        val finishOrderResponse = RecycleClientNetwork.finishOrder(oid, id)
        if (200 == finishOrderResponse.isSuccessful){
            Result.success(finishOrderResponse.isSuccessful)
        }else{
            Result.failure(RuntimeException("Response code is ${finishOrderResponse.isSuccessful}"))
        }
    }

    fun sign(aid: Int, rid: String) = fire(Dispatchers.IO){
        val activityResponse = RecycleClientNetwork.sign(aid, rid)
        if (200 == activityResponse.isSuccessful){
            Result.success(activityResponse.isSuccessful)
        }else {
            Result.failure(RuntimeException("Response code is ${activityResponse.isSuccessful}"))
        }
    }

    fun checkAllActivity(rid: String) = fire(Dispatchers.IO){
        val checkAllActivityResponse = RecycleClientNetwork.checkAllActivity(rid)
        if (1 == checkAllActivityResponse.status){
            Result.success(checkAllActivityResponse.activities)
        }else {
            Result.failure(RuntimeException("Response code is ${checkAllActivityResponse.status}"))
        }
    }

    fun checkActivityById(aid: Int,rid: String) = fire(Dispatchers.IO){
        val checkActivityByIdResponse = RecycleClientNetwork.checkActivityById(aid, rid)
        if (1 == checkActivityByIdResponse.status){
            Result.success(checkActivityByIdResponse.status)
        }else {
            Result.failure(RuntimeException("Response code is ${checkActivityByIdResponse.status}"))
        }
    }

    fun cancelSign(aid: Int, id: String) = fire(Dispatchers.IO){
        val cancelSignResponse = RecycleClientNetwork.cancelSign(aid, id)
        if (200 == cancelSignResponse.isSuccessful){
            Result.success(cancelSignResponse.isSuccessful)
        }else {
            Result.failure(RuntimeException("Response code is ${cancelSignResponse.isSuccessful}"))
        }
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