package com.wgy.recycleclient.logic.model

import com.google.gson.annotations.SerializedName

data class ActivityResponse(
        @SerializedName("code") val isSuccessful: Int,
        val content: String,
        val args: String
        )

data class Activity(
        val aid: Int,
        val rid: String
)

data class CheckAllActivityResponse(
        val status: Int,
        val activities: List<AllActivity>
)

data class AllActivity(
        val category: String,
        val startTime: String,
        val endTime: String,
        val id: Int,
        val location: String,
        val name: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val state: Int
)

data class CheckAllActivity(
        val rid: String
)

data class CheckActivityByIdResponse(
        val status: Int,
        val category: String,
        val startTime: String,
        val endTime: String,
        val id: Int,
        val location: String,
        val name: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val state: Int
)

data class CheckActivityById(
        val aid: Int,
        val rid: String
)

data class CancelSignResponse(
        @SerializedName("code") val isSuccessful: Int,
        val content: String
)

data class CancelSign(
        val aid: Int,
        val rid: String
)