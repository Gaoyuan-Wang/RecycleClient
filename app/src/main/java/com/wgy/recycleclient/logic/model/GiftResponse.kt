package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

data class CheckAllGiftResponse(val status:String, val AllGift:List<Gift>)
data class Gift(
        val id:String,
        val name:String,
        val picture:String,
        val point: Int,
        val stock: Int)
data class CheckAllGift(val id:String)

data class CheckGiftByIdResponse(val status:String, val gift:Gift)
data class CheckGiftById(val gid:Int)

data class CheckGiftByKeyResponse(val status:String, val AllGift:List<Gift>)
data class CheckGiftByKey(val key:String)

data class ExchangeGiftResponse(
        @SerializedName("code") val isSuccessful: Int,
        val content: String)
data class ExchangeGift(
        val id:String,
        val rid: Int,
        val gid:Int,
        val contact:String,
        val phone:String,
        val location:String
)

data class CancelGiftResponse(
        @SerializedName("code") val isSuccessful: Int,
        val content: String)
data class CancelGift(val id:String,val gid: Int)