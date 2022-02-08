package com.wgy.recycleclient.logic.network

import com.wgy.recycleclient.logic.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//向服务器发送请求的格式
interface LoginService {

    /**
     * @param username 用户名
     * @param password 密码
     * */
    @GET("/loginController")
    fun getLoginData(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<LoginResponse>
}