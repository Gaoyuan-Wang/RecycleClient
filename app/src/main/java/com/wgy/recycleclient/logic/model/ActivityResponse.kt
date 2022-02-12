package com.wgy.recycleclient.logic.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class ActivityResponse(
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String
        )
data class Activity(val aid: Int, val rid: String)

data class CheckAllActivityResponse(
        val success: String,
        val code: Int,
        val data: List<CheckAllActivityData>,
        val message: String
)
data class CheckAllActivityData(
        val id: Int,
        val name: String,
        val startTime: String,
        val endTime: String,
        val location: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val category: String,
        val state: Int
)
data class CheckAllActivity(val rid: String)

data class CheckActivityByCategoryResponse(
        val success: String,
        val code: Int,
        val data: List<CheckActivityByCategoryData>,
        val message: String
)
data class CheckActivityByCategoryData(
        val id: Int,
        val name: String,
        val startTime: String,
        val endTime: String,
        val location: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val category: String,
        val state: Int
)
data class CheckActivityByCategory(val category: String, val rid: String)

data class CheckActivityByIdResponse(
        val success: String,
        val code: Int,
        val data: CheckActivityByIdData,
        val message: String
)
data class CheckActivityByIdData(
        val id: Int,
        val name: String,
        val startTime: String,
        val endTime: String,
        val location: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val category: String,
        val state: Int
)
data class CheckActivityById(val aid: Int, val rid: String)

data class CheckActivityByResidentResponse(
        val success: String,
        val code: Int,
        val data: List<CheckActivityByResidentData>,
        val message: String
)
data class CheckActivityByResidentData(
        val id: Int,
        val name: String,
        val startTime: String,
        val endTime: String,
        val location: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val category: String,
        val state: Int
)
data class CheckActivityByResident(val rid: String)

data class CheckRecommendActivityResponse(
        val success: String,
        val code: Int,
        val data: List<CheckRecommendActivityData>,
        val message: String
)
data class CheckRecommendActivityData(
        val id: Int,
        val name: String,
        val startTime: String,
        val endTime: String,
        val location: String,
        val num: Int,
        val point: Int,
        val poster: String,
        val category: String,
        val state: Int
)
data class CheckRecommendActivity(val rid: String)

data class CancelSignResponse(
        val success: String,
        val code: Int,
        val data: List<Int>,
        val message: String
)
data class CancelSign(val aid: Int, val rid: String)