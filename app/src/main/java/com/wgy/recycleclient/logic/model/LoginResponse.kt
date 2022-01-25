package com.wgy.recycleclient.logic.model

//服务器响应数据格式
data class LoginResponse(
    var isAccessible: Boolean)

//请求服务器数据格式
data class Login(
    val username: String,
    val password: String)