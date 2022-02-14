package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.CheckResidentById

class PersonalInformationViewModel: ViewModel() {
    var id = ""
    var username = ""
    var password = ""
    var point = 0
    var phone = ""
    var like = 0

    val checkResidentByIdLiveData = MutableLiveData<CheckResidentById>()
    lateinit var checkResidentById: CheckResidentById
    val checkResidentByIdResponseLiveData = Transformations.switchMap(checkResidentByIdLiveData){
        Repository.checkResidentById(checkResidentById.id)
    }
    fun checkResidentById(checkResidentById: CheckResidentById){
        checkResidentByIdLiveData.value = checkResidentById
    }
}