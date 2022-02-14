package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.CheckPRById
import com.wgy.recycleclient.logic.model.CheckPRByIdData

class IntegralRecordViewModel: ViewModel() {
    var id = ""

    val checkPRByIdLiveData = MutableLiveData<CheckPRById>()
    lateinit var checkPRById: CheckPRById
    var allRecord = ArrayList<CheckPRByIdData>()
    val checkPRByIdResponseLiveData = Transformations.switchMap(checkPRByIdLiveData){
        Repository.checkPRById(checkPRById.id)
    }
    fun checkPRById(checkPRById: CheckPRById){
        checkPRByIdLiveData.value = checkPRById
    }
}