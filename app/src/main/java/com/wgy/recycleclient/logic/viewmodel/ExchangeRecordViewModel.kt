package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.CancelGift
import com.wgy.recycleclient.logic.model.CheckOnesRGR
import com.wgy.recycleclient.logic.model.CheckOnesRGRData

class ExchangeRecordViewModel: ViewModel() {
    var id=""

    val cancelGiftLiveData = MutableLiveData<CancelGift>()
    lateinit var cancelGift: CancelGift
    val cancelGiftResponseLiveData = Transformations.switchMap(cancelGiftLiveData){
        Repository.cancelGift(cancelGift.id,cancelGift.gid)
    }
    fun cancelGift(cancelGift: CancelGift){
        cancelGiftLiveData.value = cancelGift
    }

    val checkOnesRGRLiveData = MutableLiveData<CheckOnesRGR>()
    lateinit var checkOnesRGR: CheckOnesRGR
    var giftPosition = 0
    val allExchangeRecords = ArrayList<CheckOnesRGRData>()
    val checkOnesRGRResponseLiveData = Transformations.switchMap(checkOnesRGRLiveData){
        Repository.checkOnesRGR(checkOnesRGR.rid)
    }
    fun checkOnesRGR(checkOnesRGR: CheckOnesRGR){
        checkOnesRGRLiveData.value = checkOnesRGR
    }
}