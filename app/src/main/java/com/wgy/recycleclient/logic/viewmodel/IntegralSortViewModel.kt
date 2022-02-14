package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.SortResidentByLike
import com.wgy.recycleclient.logic.model.SortResidentByLikeData

class IntegralSortViewModel: ViewModel() {
    var id =""

    val sortResidentByLikeLiveData = MutableLiveData<SortResidentByLike>()
    lateinit var sortResidentByLike: SortResidentByLike
    var allSortResidents= ArrayList<SortResidentByLikeData>()
    val sortResidentByLikeResponseLiveData = Transformations.switchMap(sortResidentByLikeLiveData){
        Repository.sortResidentByLike(sortResidentByLike.id)
    }
    fun sortResidentByLike(sortResidentByLike: SortResidentByLike){
        sortResidentByLikeLiveData.value = sortResidentByLike
    }
}