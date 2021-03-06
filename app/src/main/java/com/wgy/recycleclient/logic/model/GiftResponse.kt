package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

data class CheckAllGiftResponse(
        val success: String,
        val code: Int,
        val data: List<CheckAllGiftData>,
        val message: String
)
data class CheckAllGiftData(
        val id: Int,
        val picture:String,
        val name:String,
        val point: Int,
        val stock: Int)
data class CheckAllGift(val id:String)

data class CheckGiftByIdResponse(
        val success: String,
        val code: Int,
        val data: CheckGiftByIdData,
        val message: String
)
data class CheckGiftByIdData(
        val id: Int,
        val picture:String,
        val name:String,
        val point: Int,
        val stock: Int)
data class CheckGiftById(val gid:Int)

data class CheckGiftByKeyResponse(
        val success: String,
        val code: Int,
        val data: List<CheckGiftByKeyData>,
        val message: String
)
data class CheckGiftByKeyData(
        val id: Int,
        val picture:String,
        val name:String,
        val point: Int,
        val stock: Int)
data class CheckGiftByKey(val key:String)

data class CheckOnesRGRResponse(
        val success: String,
        val code: Int,
        val data: List<CheckOnesRGRData>,
        val message: String
)
data class CheckOnesRGRData(
        val id: Int,
        val rid: String,
        val gid: Int,
        val location: String,
        val contact: String,
        val phone: String
)
data class CheckOnesRGR(val rid:String)

data class ExchangeGiftResponse(
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String)
data class ExchangeGift(
        val id:String,
        val rid: String,
        val gid:Int,
        val contact:String,
        val phone:String,
        val location:String
)

data class CancelGiftResponse(
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String)
data class CancelGift(val id:String,val gid: Int)