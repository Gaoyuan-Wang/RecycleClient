package com.wgy.recycleclient.logic.model

data class AppointmentResponse (
    val isSuccessful: Boolean)

data class Appointment(
    val date: String,
    val time: String,
    val address: String
)