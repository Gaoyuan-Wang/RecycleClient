package com.wgy.recycleclient.logic.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.RecycleClientApplication.Companion.context
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.*

class HomeViewModel: ViewModel() {
    var date:String = ""
    var time:String = ""
    var location:String = ""
    var amount:Int = 0
    //获取当前登录的用户ID
    var id: String = context.getSharedPreferences(
            "RegisterAccount",
            AppCompatActivity.MODE_PRIVATE
    ).getString("id", null).toString()

    var appointmentLiveData = MutableLiveData<Appointment>()
    lateinit var appointment: Appointment

    var ordersLiveData = MutableLiveData<CheckOrder>()
    var checkOrder = CheckOrder(id)
    var allOrders = ArrayList<Order>()

    var finishOrderLiveData = MutableLiveData<FinishOrder>()
    lateinit var finishOrder: FinishOrder

    var activityLiveData = MutableLiveData<Activity>()
    lateinit var activity: Activity

    var checkAllActivityLiveData = MutableLiveData<CheckAllActivity>()
    var checkAllActivity = CheckAllActivity(id)
    var allActivity = ArrayList<AllActivity>()

    var checkActivityByIdLiveData = MutableLiveData<CheckActivityById>()
    lateinit var checkActivityById: CheckActivityById

    var cancelSignLiveData = MutableLiveData<CancelSign>()
    lateinit var cancelSign: CancelSign

    val appointIsSuccessful = Transformations.switchMap(appointmentLiveData){ appointment->
        Repository.appointOrder(
                appointment.id,
                appointment.rid,
                appointment.location,
                appointment.time,
                appointment.amount,
                appointment.point,
                appointment.state
        )
    }
    fun appointOrder(appointment: Appointment){
        appointmentLiveData.value = appointment
    }

    val orders = Transformations.switchMap(ordersLiveData){checkOrder ->
        Repository.checkOrder(checkOrder.id)
    }
    fun checkOrder(checkOrder: CheckOrder){
        ordersLiveData.value = checkOrder
    }

    val finishOrderIsSuccessful = Transformations.switchMap(finishOrderLiveData){finishOrder ->
        Repository.finishOrder(finishOrder.oid, finishOrder.rid)
    }
    fun finishOrder(finishOrder: FinishOrder){
        finishOrderLiveData.value = finishOrder
    }

    val signIsSuccessful = Transformations.switchMap(activityLiveData){
        Repository.sign(activity.aid,activity.rid)
    }
    fun sign(activity: Activity){
        activityLiveData.value = activity
    }

    val activities = Transformations.switchMap(checkAllActivityLiveData){checkAllActivity ->
        Repository.checkAllActivity(checkAllActivity.rid)
    }
    fun checkAllActivity(checkAllActivity: CheckAllActivity){
        checkAllActivityLiveData.value = checkAllActivity
    }

    var activityById = Transformations.switchMap(checkActivityByIdLiveData){checkActivityById ->
        Repository.checkActivityById(checkActivityById.aid, checkActivityById.rid)
    }
    fun checkActivityById(checkActivityById: CheckActivityById){
        checkActivityByIdLiveData.value = checkActivityById
    }

    var cancelSignIsSuccessful = Transformations.switchMap(cancelSignLiveData){cancelSign->
        Repository.cancelSign(cancelSign.aid, cancelSign.rid)
    }
    fun cancelSign(cancelSign:CancelSign){
        cancelSignLiveData.value = cancelSign
    }
}