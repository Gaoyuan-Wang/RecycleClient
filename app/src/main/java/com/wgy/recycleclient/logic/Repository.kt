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
            //Result.success(loginResponse.id)
        }else{
            Result.failure(RuntimeException("Response accessibility is ${loginResponse.success}"))
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
        Log.d("Repository", activityResponse.content)
        if (1 == activityResponse.isSuccessful){
            Result.success(activityResponse.content)
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

    fun checkAllGift(rid: String) = fire(Dispatchers.IO){
        val checkAllGiftResponse = RecycleClientNetwork.checkAllGift(rid)
        if ("true" == checkAllGiftResponse.status){
            Result.success(checkAllGiftResponse.AllGift)
        }else {
            Result.failure(RuntimeException("Response code is ${checkAllGiftResponse.status}"))
        }
    }

    fun checkGiftById(gid: Int) = fire(Dispatchers.IO){
        val checkGiftByIdResponse = RecycleClientNetwork.checkGiftById(gid)
        if ("true" == checkGiftByIdResponse.status){
            Result.success(checkGiftByIdResponse.gift)
        }else {
            Result.failure(RuntimeException("Response code is ${checkGiftByIdResponse.status}"))
        }
    }

    fun checkGiftByKey(key: String) = fire(Dispatchers.IO){
        val checkGiftByKeyResponse = RecycleClientNetwork.checkAllGift(key)
        if ("true" == checkGiftByKeyResponse.status){
            Result.success(checkGiftByKeyResponse.AllGift)
        }else {
            Result.failure(RuntimeException("Response code is ${checkGiftByKeyResponse.status}"))
        }
    }

    fun exchangeGift(id: Int, rid: String, gid: Int, contact:String, phone:String, location:String) = fire(Dispatchers.IO){
        val exchangeGiftResponse = RecycleClientNetwork.exchangeGift(id, rid, gid, contact, phone, location)
        if(1 == exchangeGiftResponse.isSuccessful){
            Result.success(exchangeGiftResponse.content)
        }else {
            Result.failure(RuntimeException("Response code is ${exchangeGiftResponse.isSuccessful}"))}
    }

    fun cancelGift(id: String, gid: Int) = fire(Dispatchers.IO){
        val cancelGiftResponse = RecycleClientNetwork.cancelGift(id, gid)
        if(1 == cancelGiftResponse.isSuccessful){
            Result.success(cancelGiftResponse.content)
        }else {
            Result.failure(RuntimeException("Response code is ${cancelGiftResponse.isSuccessful}"))}
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