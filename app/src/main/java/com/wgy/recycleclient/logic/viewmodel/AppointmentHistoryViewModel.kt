package com.wgy.recycleclient.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.CheckOrder
import com.wgy.recycleclient.logic.model.FinishOrder
import com.wgy.recycleclient.logic.model.Order

class AppointmentHistoryViewModel: ViewModel() {
    //获取当前登录的用户ID
    var id: String = ""

    var ordersLiveData = MutableLiveData<CheckOrder>()
    var checkOrder = CheckOrder(id)
    var allOrders = ArrayList<Order>()

    var finishOrderLiveData = MutableLiveData<FinishOrder>()
    lateinit var finishOrder: FinishOrder


    val orders = Transformations.switchMap(ordersLiveData){ checkOrder ->
        Repository.checkOrder(checkOrder.id)
    }
    fun checkOrder(checkOrder: CheckOrder){
        ordersLiveData.value = checkOrder
    }

    val finishOrderIsSuccessful = Transformations.switchMap(finishOrderLiveData){ finishOrder ->
        Repository.finishOrder(finishOrder.oid, finishOrder.rid)
    }
    fun finishOrder(finishOrder: FinishOrder){
        finishOrderLiveData.value = finishOrder
    }
}