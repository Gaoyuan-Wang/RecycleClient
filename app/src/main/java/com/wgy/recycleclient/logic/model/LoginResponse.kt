package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

//服务器响应数据格式
data class LoginResponse(
    @SerializedName("code") val isAccessible: Int,
    val id: String)

//请求服务器数据格式
data class Login(
    val username: String,
    val password: String)