package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

data class AppointmentResponse (
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String
        )
data class Appointment(
        val checkId: String,
        val rid: String,
        val location: String,
        val amount: Int,
        val time: String,
        val point: Int,
        )

data class CheckOrderResponse(
        val success: String,
        val code: Int,
        val data: List<Order>,
        val message: String
)
data class Order(
        val id: Int,
        val rid: String,
        val location: String,
        val time: String,
        val amount: Int,
        val point: Int,
        val state: Int
        )
data class CheckOrder(val id: String)

data class FinishOrderResponse(
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String
        )
data class FinishOrder(
        val oid: Int,
        val rid: String
        )