package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.*

class ActivityHistoryViewModel: ViewModel() {
    var id = ""
    var activityPosition = 0
    var allActivities = ArrayList<CheckActivityByResidentData>()

    val checkActivityByResidentLiveData = MutableLiveData<CheckActivityByResident>()
    lateinit var checkActivityByResident: CheckActivityByResident
    val activityLiveData = Transformations.switchMap(checkActivityByResidentLiveData) {
        Repository.checkActivityByResident(checkActivityByResident.rid)
    }
    fun checkActivityByResident(checkActivityByResident: CheckActivityByResident){
        checkActivityByResidentLiveData.value = checkActivityByResident
    }

    var cancelSignLiveData = MutableLiveData<CancelSign>()
    lateinit var cancelSign: CancelSign
    val cancelSignResponseLiveData = Transformations.switchMap(cancelSignLiveData){
        Repository.cancelSign(cancelSign.aid,cancelSign.rid)
    }
    fun cancelSign(cancelSign: CancelSign){
        cancelSignLiveData.value = cancelSign
    }
}