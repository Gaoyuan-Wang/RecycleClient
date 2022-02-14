package com.wgy.recycleclient.logic.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.*

class GiftViewModel: ViewModel() {
    var id = ""
    var integral = 0
    var contact = ""
    var location = ""
    var phone = ""

    val checkResidentByIdLiveData = MutableLiveData<CheckResidentById>()
    lateinit var checkResidentById:CheckResidentById
    val checkResidentByIdResponseLiveData = Transformations.switchMap(checkResidentByIdLiveData){
        Repository.checkResidentById(checkResidentById.id)
    }
    fun checkResidentById(checkResidentById: CheckResidentById){
        checkResidentByIdLiveData.value = checkResidentById
    }

    val checkAllGiftLiveData = MutableLiveData<CheckAllGift>()
    lateinit var checkAllGift: CheckAllGift
    val allGift = ArrayList<CheckAllGiftData>()
    val checkAllGiftResponseLiveData = Transformations.switchMap(checkAllGiftLiveData){
        Repository.checkAllGift(checkAllGift.id)
    }
    fun checkAllGift(checkAllGift: CheckAllGift){
        checkAllGiftLiveData.value = checkAllGift
    }

    val exchangeGiftLiveData = MutableLiveData<ExchangeGift>()
    lateinit var exchangeGift: ExchangeGift
    val exchangeGiftResponseLiveData = Transformations.switchMap(exchangeGiftLiveData){
        Repository.exchangeGift(exchangeGift.id,exchangeGift.rid,exchangeGift.gid,exchangeGift.contact,exchangeGift.phone,exchangeGift.location)
    }
    fun exchangeGift(exchangeGift: ExchangeGift){
        exchangeGiftLiveData.value = exchangeGift
    }

    val checkGiftByIdLiveData = MutableLiveData<CheckGiftById>()
    lateinit var checkGiftById: CheckGiftById
    val allCheckIdGift = ArrayList<CheckGiftByIdData>()
    val checkGiftByIdResponseLiveData = Transformations.switchMap(checkGiftByIdLiveData){
        Log.d("GiftViewModel","1")
        Repository.checkGiftById(checkGiftById.gid)
    }
    fun checkGiftById(checkGiftById: CheckGiftById){
        Log.d("GiftViewModel","1")
        checkGiftByIdLiveData.value = checkGiftById
    }

    val checkGiftByKeyLiveData = MutableLiveData<CheckGiftByKey>()
    lateinit var checkGiftByKey: CheckGiftByKey
    val allCheckKeyGift = ArrayList<CheckGiftByKeyData>()
    val checkGiftByKeyResponseLiveData = Transformations.switchMap(checkGiftByKeyLiveData){
        Repository.checkGiftByKey(checkGiftByKey.key)
    }
    fun checkGiftByKey(checkGiftByKey: CheckGiftByKey){
        checkGiftByKeyLiveData.value = checkGiftByKey
    }
}