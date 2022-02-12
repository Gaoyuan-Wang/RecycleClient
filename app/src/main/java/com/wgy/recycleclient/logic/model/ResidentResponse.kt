package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

//服务器响应数据格式
data class LoginResponse(
    val success: String,
    val code: Int,
    val data: List<String>,
    val message: String
    )
//请求服务器数据格式
data class Login(
    val username: String,
    val password: String
    )

data class CheckPRByIdResponse(
    val success: String,
    val code: Int,
    val data: List<CheckPRByIdData>,
    val message: String
    )
data class CheckPRByIdData(
    val id:Int,
    val typeId:Int,
    val point:Int,
    val rid:String
    )
data class CheckPRById(val id: String)


data class CheckResidentByIdResponse(
        val success: String,
        val code: Int,
        val data: CheckResidentByIdData,
        val message: String
)
data class CheckResidentByIdData(
        val rid:Int,
        val username:String,
        val password:String,
        val picture:String,
        val point: Int,
        val phone: String,
        val like: Int
)
data class CheckResidentById(val id: String)


data class SortResidentByLikeResponse(
        val success: String,
        val code: Int,
        val data: List<SortResidentByLikeData>,
        val message: String
)
data class SortResidentByLikeData(
        val rid:Int,
        val username:String,
        val password:String,
        val picture:String,
        val point: Int,
        val phone: String,
        val like: Int
)
data class SortResidentByLike(val id: String)