package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.*

class ActivityViewModel: ViewModel() {
    var id = ""
    var activityCategoryList = arrayListOf("知识竞赛","游戏竞赛","社区活动")

    var signLiveData = MutableLiveData<Sign>()
    lateinit var sign: Sign
    val signResponseLiveData = Transformations.switchMap(signLiveData){
        Repository.sign(sign.aid,sign.rid)
    }
    fun sign(sign: Sign){
        signLiveData.value = sign
    }

    val checkRecommendActivityLiveData = MutableLiveData<CheckRecommendActivity>()
    lateinit var checkRecommendActivity: CheckRecommendActivity
    var allRecommendActivities = ArrayList<CheckRecommendActivityData>()
    val checkRecommendActivityResponseLiveData = Transformations.switchMap(checkRecommendActivityLiveData){
        Repository.checkRecommendActivity(checkRecommendActivity.rid)
    }
    fun checkRecommendActivity(checkRecommendActivity: CheckRecommendActivity){
        checkRecommendActivityLiveData.value = checkRecommendActivity
    }

    var checkAllActivityLiveData = MutableLiveData<CheckAllActivity>()
    lateinit var checkAllActivity: CheckAllActivity
    var allActivity = ArrayList<CheckAllActivityData>()



    var checkActivityByIdLiveData = MutableLiveData<CheckActivityById>()
    lateinit var checkActivityById: CheckActivityById
    var IdActivity = ArrayList<CheckActivityByIdData>()
    var checkActivityByIdResponseLiveData = Transformations.switchMap(checkActivityByIdLiveData){ checkActivityById ->
        Repository.checkActivityById(checkActivityById.aid, checkActivityById.rid)
    }
    fun checkActivityById(checkActivityById: CheckActivityById){
        checkActivityByIdLiveData.value = checkActivityById
    }


    val activities = Transformations.switchMap(checkAllActivityLiveData){ checkAllActivity ->
        Repository.checkAllActivity(checkAllActivity.rid)
    }
    fun checkAllActivity(checkAllActivity: CheckAllActivity){
        checkAllActivityLiveData.value = checkAllActivity
    }




    var cancelSignLiveData = MutableLiveData<CancelSign>()
    lateinit var cancelSign: CancelSign
    var cancelSignIsSuccessful = Transformations.switchMap(cancelSignLiveData){ cancelSign->
        Repository.cancelSign(cancelSign.aid, cancelSign.rid)
    }
    fun cancelSign(cancelSign: CancelSign){
        cancelSignLiveData.value = cancelSign
    }
}