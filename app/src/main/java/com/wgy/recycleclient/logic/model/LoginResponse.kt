package com.wgy.recycleclient.logic.model

//服务器响应数据格式
data class LoginResponse(
    val isAccessible: Boolean,
    val id: String)

//请求服务器数据格式
data class Login(
    val username: String,
    val password: String)