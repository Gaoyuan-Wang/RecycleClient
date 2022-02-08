package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

//服务器响应数据格式
data class LoginResponse(
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String)

//请求服务器数据格式
data class Login(
    val username: String,
    val password: String)