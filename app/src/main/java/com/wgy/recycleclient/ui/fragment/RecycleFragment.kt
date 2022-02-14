package com.wgy.recycleclient.ui.fragment

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.Appointment
import com.wgy.recycleclient.logic.viewmodel.AppointmentHistoryViewModel
import com.wgy.recycleclient.logic.viewmodel.HomeViewModel
import com.wgy.recycleclient.logic.viewmodel.RecycleViewModel
import com.wgy.recycleclient.ui.activity.AppointmentHistoryActivity
import kotlinx.android.synthetic.main.fragment_recycle.*

//预约界面
@RequiresApi(Build.VERSION_CODES.N)
class RecycleFragment: Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(RecycleViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.id = activity?.getSharedPreferences(
                "RegisterAccount",
                AppCompatActivity.MODE_PRIVATE
        )?.getString("id", null).toString()

        initListener()

        //是否预约成功
        viewModel.appointIsSuccessful.observe(viewLifecycleOwner, { result ->
            val isSuccessful = result.getOrNull()
            if (isSuccessful != null) {
                Toast.makeText(activity, "您已成功预约！", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "您未成功预约", Toast.LENGTH_SHORT).show()}
        })
    }

    private fun initListener(){
        //选择日期
        date.setOnClickListener{
            val c = Calendar.getInstance()
            c.timeInMillis = System.currentTimeMillis()
            val year = c[Calendar.YEAR]
            val month = c[Calendar.MONTH]
            val day = c[Calendar.DAY_OF_MONTH]

            val dialog = DatePickerDialog(
                requireActivity(), { _, year, month, day ->
                    viewModel.date = StringBuffer().append(
                        String.format(
                            "%d-%02d-%02d",
                            year,
                            month + 1,
                            day
                        )
                    ).toString()
                    date.text = viewModel.date
                    Toast.makeText(activity, "$year:$month:$day", Toast.LENGTH_SHORT).show()
                },
                year,
                month,
                day
            )
            dialog.show()
        }

        //选择时间
        time.setOnClickListener{
            val dialog = TimePickerDialog(
                requireActivity(),
                { _, hourOfDay, minute ->
                    viewModel.time = StringBuilder().append(
                        if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                    ).append(":").append(
                        if (minute < 10) "0$minute" else minute
                    ).append(":00").toString()
                    time.text = viewModel.time
                }, 0, 0, true
            )
            dialog.show()
        }

        //预约
        appointment.setOnClickListener{
            viewModel.location = address.text.toString()
            if("" != amount.text.toString()) {
                viewModel.amount = Integer.parseInt(amount.text.toString())
            }else{
                viewModel.amount = 0}
            if("" == viewModel.time || "" == viewModel.date){
                Toast.makeText(activity,"请输入预约时间",Toast.LENGTH_SHORT).show()
            }else if ("" == viewModel.location){
                Toast.makeText(activity,"请输入预约地点",Toast.LENGTH_SHORT).show()
            }else{
                AlertDialog.Builder(requireActivity()).apply {
                    setCancelable(false)
                    setMessage("请您确定预约信息：\n时间：${viewModel.date} ${viewModel.time}\n地址：${viewModel.location}\n数量：${viewModel.amount}")
                    Log.d("RecycleFragment",viewModel.id)
                    setPositiveButton("确 定"){ _, _->
                        viewModel.appointment = Appointment(viewModel.id,viewModel.id,viewModel.location,viewModel.amount, viewModel.date + "-" + viewModel.time, 2 * viewModel.amount)
                        viewModel.appointOrder(viewModel.appointment)
                    }
                    setNegativeButton("取 消"){ _, _-> }
                    show()
                }
            }
        }

        //历史订单
        history.setOnClickListener{
            val intent = Intent(activity, AppointmentHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}