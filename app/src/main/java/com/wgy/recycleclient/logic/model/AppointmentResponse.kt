package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

data class AppointmentResponse (
        @SerializedName("code") val isSuccessful: Int,
        val content: String
        )

data class Appointment(
        val id: Int,
        val rid: String,
        val location: String,
        val time: String,
        val amount: Int,
        val point: Int,
        val state: Int
        )

data class CheckOrderResponse(
        val status: Int,
        val orders: List<Order>
)

data class Order(
        val amount: Int,
        val id: Int,
        val location: String,
        val point: Int,
        val rid: String,
        val state: Int,
        val time: String
        )

data class CheckOrder(
        val id: String
        )

data class FinishOrderResponse(
        @SerializedName("code") val isSuccessful: Int,
        val content: String
        )

data class FinishOrder(
        val oid: Int,
        val rid: String
        )