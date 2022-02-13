package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.Sign
import com.wgy.recycleclient.logic.model.CheckActivityByCategory
import com.wgy.recycleclient.logic.model.CheckActivityByCategoryData
import com.wgy.recycleclient.logic.model.Order

class ActivityCategoryViewModel: ViewModel() {
    var id = ""
    var category = ""
    var activityPosition = 0
    var allActivities = ArrayList<CheckActivityByCategoryData>()

    val checkActivityByCategoryLiveData = MutableLiveData<CheckActivityByCategory>()
    lateinit var checkActivityByCategory: CheckActivityByCategory
    val activityLiveData = Transformations.switchMap(checkActivityByCategoryLiveData) {
        Repository.checkActivityByCategory(checkActivityByCategory.category,checkActivityByCategory.rid)
    }
    fun checkActivityByCategory(checkActivityByCategory: CheckActivityByCategory){
        checkActivityByCategoryLiveData.value = checkActivityByCategory
    }

    var signLiveData = MutableLiveData<Sign>()
    lateinit var sign: Sign
    val signResponseLiveData = Transformations.switchMap(signLiveData){
        Repository.sign(sign.aid,sign.rid)
    }
    fun sign(sign: Sign){
        signLiveData.value = sign
    }
}