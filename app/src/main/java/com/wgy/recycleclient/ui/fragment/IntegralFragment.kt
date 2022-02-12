package com.wgy.recycleclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.viewmodel.GiftViewModel

class IntegralFragment:Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(GiftViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_integral, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.id = activity?.getSharedPreferences(
                "RegisterAccount",
                AppCompatActivity.MODE_PRIVATE
        )?.getString("id", null).toString()
    }
}