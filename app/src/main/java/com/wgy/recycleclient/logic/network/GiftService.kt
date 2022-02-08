package com.wgy.recycleclient.logic.network

import com.wgy.recycleclient.logic.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiftService {
    /**
     * @param id 用户名
     */
    @GET("/checkAllGift")
    fun checkAllGift(
            @Query("rid")id: String
    ): Call<CheckAllGiftResponse>

    /**
     * @param gid 礼品编号
     */
    @GET("/checkGiftById")
    fun checkGiftById(
            @Query("gid")gid: Int
    ): Call<CheckGiftByIdResponse>

    /**
     * @param key 搜索关键字
     */
    @GET("/checkGiftByKey")
    fun checkGiftByKey(
            @Query("key")key: String
    ): Call<CheckGiftByKeyResponse>

    /**
     * @param id 兑换单号
     * @param rid 用户编号
     * @param gid 礼物编号
     * @param contact 联系人姓名
     * @param phone 电话号码
     * @param location 位置
     */
    @GET("/exchangeGift")
    fun exchangeGift(
            @Query("id") id: Int,
            @Query("rid") rid: String,
            @Query("gid") gid: Int,
            @Query("contact") contact:String,
            @Query("phone") phone:String,
            @Query("location") location:String
    ): Call<ExchangeGiftResponse>

    /**
     * @param id 用户名
     * @param gid 礼品编号
     */
    @GET("/cancelGift")
    fun cancelGift(
            @Query("rid")id: String,
            @Query("gid")gid: Int
    ): Call<CancelGiftResponse>
}