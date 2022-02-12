package com.wgy.recycleclient.logic.network

import com.wgy.recycleclient.logic.model.AppointmentResponse
import com.wgy.recycleclient.logic.model.CheckOrderResponse
import com.wgy.recycleclient.logic.model.FinishOrderResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface AppointmentService {
    /**
     * @param checkId  用户ID
     * @param rid      用户ID
     * @param location 回收地址
     * @param time     预定时间
     * @param amount   回收数量
     * @param point    积分数
     */
    @GET("/appointOrder")
    fun appointOrder(
            @Query("checkId")checkId: String,
            @Query("rid")rid: String,
            @Query("location")location: String,
            @Query("time")time: String,
            @Query("amount")amount: Int,
            @Query("point")point: Int,
    ): Call<AppointmentResponse>

    /**
     * @param id 居民ID
     */
    @GET("/checkOrder")
    fun checkOrder(
            @Query("id")id: String
    ): Call<CheckOrderResponse>

    /**
     * @param oid 订单号
     * @param id  居民ID
     */
    @GET("/finishOrder")
    fun finishOrder(
            @Query("oid")oid: Int,
            @Query("rid")id: String
    ): Call<FinishOrderResponse>
}