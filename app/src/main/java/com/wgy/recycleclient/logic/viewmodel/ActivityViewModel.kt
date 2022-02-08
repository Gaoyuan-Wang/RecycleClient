package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.*

class ActivityViewModel: ViewModel() {
    var id = ""

    var activityLiveData = MutableLiveData<Activity>()
    lateinit var activity: Activity

    var checkAllActivityLiveData = MutableLiveData<CheckAllActivity>()
    var checkAllActivity = CheckAllActivity(id)
    var allActivity = ArrayList<AllActivity>()

    var checkActivityByIdLiveData = MutableLiveData<CheckActivityById>()
    lateinit var checkActivityById: CheckActivityById

    var cancelSignLiveData = MutableLiveData<CancelSign>()
    lateinit var cancelSign: CancelSign

    val signIsSuccessful = Transformations.switchMap(activityLiveData){
        Repository.sign(activity.aid,activity.rid)
//        Repository.sign(1,"1")
    }
    fun sign(activity: Activity){
//    fun sign(){
        activityLiveData.value = activityLiveData.value
    }

    val activities = Transformations.switchMap(checkAllActivityLiveData){ checkAllActivity ->
        Repository.checkAllActivity(checkAllActivity.rid)
    }
    fun checkAllActivity(checkAllActivity: CheckAllActivity){
        checkAllActivityLiveData.value = checkAllActivity
    }

    var activityById = Transformations.switchMap(checkActivityByIdLiveData){ checkActivityById ->
        Repository.checkActivityById(checkActivityById.aid, checkActivityById.rid)
    }
    fun checkActivityById(checkActivityById: CheckActivityById){
        checkActivityByIdLiveData.value = checkActivityById
    }

    var cancelSignIsSuccessful = Transformations.switchMap(cancelSignLiveData){ cancelSign->
        Repository.cancelSign(cancelSign.aid, cancelSign.rid)
    }
    fun cancelSign(cancelSign: CancelSign){
        cancelSignLiveData.value = cancelSign
    }
}