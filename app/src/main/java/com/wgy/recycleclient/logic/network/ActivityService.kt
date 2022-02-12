package com.wgy.recycleclient.logic.network

import com.wgy.recycleclient.logic.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityService {
    /**
     * @param aid 活动ID
     * @param id  用户ID
     */
    @GET("/sign")
    fun sign(
            @Query("aid")aid: Int,
            @Query("rid")id: String
    ): Call<ActivityResponse>

    /**
     * @param id 用户ID
     */
    @GET("/checkAllActivity")
    fun checkAllActivity(
            @Query("rid")id: String
    ): Call<CheckAllActivityResponse>

    /**
     * @param aid 活动ID
     * @param id  用户ID
     */
    @GET("/checkActivityById")
    fun checkActivityById(
            @Query("aid")aid: Int,
            @Query("rid")id: String
    ): Call<CheckActivityByIdResponse>

    /**
     * @param category 活动类型
     * @param id       用户ID
     */
    @GET("/checkActivityByCategory")
    fun checkActivityByCategory(
            @Query("category")category: String,
            @Query("rid")id: String
    ): Call<CheckActivityByCategoryResponse>

    /**
     * @param id  用户ID
     */
    @GET("/checkActivityByResident")
    fun checkActivityByResident(
            @Query("rid")id: String
    ): Call<CheckActivityByResidentResponse>

    /**
     * @param id  用户ID
     */
    @GET("/checkRecommendActivity")
    fun checkRecommendActivity(
            @Query("rid")id: String
    ): Call<CheckRecommendActivityResponse>

    /**
     * @param aid 活动ID
     * @param id  用户ID
     */
    @GET("/cancelSign")
    fun cancelSign(
            @Query("aid")aid: Int,
            @Query("rid")id: String
    ): Call<CancelSignResponse>
}