package com.wgy.recycleclient.logic.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recycleclient.logic.Repository
import com.wgy.recycleclient.logic.model.Login

class LoginViewModel: ViewModel() {
    private var loginLiveData = MutableLiveData<Login>()
    lateinit var login : Login

    val loginResult = Transformations.switchMap(loginLiveData){
        login ->
//        Log.d("LoginViewModel", login.username)
        Repository.getLoginData(login.username,login.password)
    }

    fun getLoginData(login: Login){
        loginLiveData.value = login
    }
}