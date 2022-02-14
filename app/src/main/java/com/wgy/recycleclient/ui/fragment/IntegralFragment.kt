package com.wgy.recycleclient.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.*
import com.wgy.recycleclient.logic.viewmodel.GiftViewModel
import com.wgy.recycleclient.ui.activity.ExchangeRecordActivity
import com.wgy.recycleclient.ui.activity.IntegralSortActivity
import com.wgy.recycleclient.ui.adapter.GiftAdapter
import com.wgy.recycleclient.ui.adapter.GiftIdAdapter
import com.wgy.recycleclient.ui.adapter.GiftKeyAdapter
import kotlinx.android.synthetic.main.fragment_activity.*
import kotlinx.android.synthetic.main.fragment_integral.*
import java.util.regex.Pattern

class IntegralFragment:Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(GiftViewModel::class.java)}
    lateinit var giftAdapter: GiftAdapter
    lateinit var giftIdAdapter: GiftIdAdapter
    lateinit var giftKeyAdapter: GiftKeyAdapter

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

        viewModel.checkResidentById = CheckResidentById(viewModel.id)
        viewModel.checkResidentById(viewModel.checkResidentById)
        viewModel.checkResidentByIdResponseLiveData.observe(viewLifecycleOwner, { result ->
            val checkResidentByIdResult = result.getOrNull()
            if (null != checkResidentByIdResult) {
                integral.text = "${checkResidentByIdResult.point}"
            } else {
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        marketRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        viewModel.checkAllGift = CheckAllGift(viewModel.id)
        viewModel.checkAllGift(viewModel.checkAllGift)
        giftAdapter = GiftAdapter(this, viewModel.allGift)
        marketRecyclerView.adapter = giftAdapter
        viewModel.checkAllGiftResponseLiveData.observe(viewLifecycleOwner, { result ->
            val allGiftResult = result.getOrNull()
            if (null != allGiftResult) {
                viewModel.allGift.clear()
                viewModel.allGift.addAll(allGiftResult)
                giftAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何推荐商品", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.exchangeGiftResponseLiveData.observe(viewLifecycleOwner, { result ->
            val exchangeGiftResult = result.getOrNull()
            if (null != exchangeGiftResult) {
                viewModel.checkResidentById(viewModel.checkResidentById)
                Toast.makeText(activity, "成功兑换商品", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "兑换商品失败", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        searchGift.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val matcher = Pattern.compile("[0-9]+").matcher(searchGift.text.toString())
                if (!matcher.matches()) {
                    viewModel.checkGiftByKey = CheckGiftByKey(searchGift.text.toString())
                    viewModel.checkGiftByKey(viewModel.checkGiftByKey)
                    searchRecyclerView.adapter = giftKeyAdapter
                }else {
                    viewModel.checkGiftById = CheckGiftById(Integer.parseInt(searchGift.text.toString()))
                    Log.d("IntegralFragment",viewModel.checkGiftById.toString())
                    viewModel.checkGiftById(viewModel.checkGiftById)
                    searchRecyclerView.adapter = giftIdAdapter
                }
                return@OnEditorActionListener true
            }
            false
        })

        searchRecyclerView.layoutManager = LinearLayoutManager(activity)
        giftIdAdapter = GiftIdAdapter(this,viewModel.allCheckIdGift)
        giftKeyAdapter = GiftKeyAdapter(this,viewModel.allCheckKeyGift)
        viewModel.checkGiftByIdResponseLiveData.observe(viewLifecycleOwner,{result->
            val idGiftResult = result.getOrNull()
            if(null != idGiftResult){
                searchRecyclerView.visibility = View.VISIBLE
                viewModel.allCheckIdGift.clear()
                viewModel.allCheckIdGift.addAll(listOf(idGiftResult))
                giftIdAdapter.notifyDataSetChanged()
            }else {
                Toast.makeText(activity, "未能查询到任何商品", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        viewModel.checkGiftByKeyResponseLiveData.observe(viewLifecycleOwner,{result->
            val keyGiftResult = result.getOrNull()
            if(null != keyGiftResult){
                searchRecyclerView.visibility = View.VISIBLE
                viewModel.allCheckKeyGift.clear()
                viewModel.allCheckKeyGift.addAll(keyGiftResult)
                giftIdAdapter.notifyDataSetChanged()
            }else {
                Toast.makeText(activity, "未能查询到任何商品", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        record.setOnClickListener {
            val intent = Intent(activity,ExchangeRecordActivity::class.java)
            startActivity(intent)
        }

        sort.setOnClickListener {
            val intent = Intent(activity,IntegralSortActivity::class.java)
            startActivity(intent)
        }
    }
}