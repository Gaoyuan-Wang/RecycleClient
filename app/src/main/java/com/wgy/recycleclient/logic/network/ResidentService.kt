package com.wgy.recycleclient.logic.network

import com.wgy.recycleclient.logic.model.CheckPRByIdResponse
import com.wgy.recycleclient.logic.model.CheckResidentByIdResponse
import com.wgy.recycleclient.logic.model.LoginResponse
import com.wgy.recycleclient.logic.model.SortResidentByLikeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//向服务器发送请求的格式
interface ResidentService {

    /**
     * @param username 用户名
     * @param password 密码
     * */
    @GET("/loginController")
    fun getLoginData(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<LoginResponse>

    /**
     * @param id 用户ID
     */
    @GET("/checkPRById")
    fun checkPRById(
        @Query("rid") id: String
    ): Call<CheckPRByIdResponse>

    /**
     * @param id 用户ID
     */
    @GET("/checkResidentById")
    fun checkResidentById(
        @Query("rid") id: String
    ): Call<CheckResidentByIdResponse>

    /**
     * @param id 用户ID
     */
    @GET("sortResidentByLike")
    fun sortResidentByLike(
        @Query("rid") id: String
    ): Call<SortResidentByLikeResponse>
}