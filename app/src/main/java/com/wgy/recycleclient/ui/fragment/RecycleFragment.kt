package com.wgy.recycleclient.ui.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.Appointment
import com.wgy.recycleclient.logic.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_recycle.*

//预约界面
@RequiresApi(Build.VERSION_CODES.N)
class RecycleFragment: Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListener()
        //是否预约成功
        viewModel.isSuccessful.observe(viewLifecycleOwner, { result ->
            val isSuccessful = result.getOrNull()
            if (isSuccessful != null) {
                Toast.makeText(activity, "您已成功预约！", Toast.LENGTH_SHORT).show()
            }
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
            viewModel.address = address.text.toString()
            viewModel.appointment = Appointment(viewModel.userName, viewModel.date, viewModel.time, viewModel.address)

            AlertDialog.Builder(requireActivity()).apply {
                setCancelable(false)
                setMessage("请您确定预约信息：\n时间：${viewModel.date} ${viewModel.time}\n地址：${viewModel.address}")
                setPositiveButton("确 定"){ _, _->
                    viewModel.makeAppointment(viewModel.appointment)
                }
                setNegativeButton("取 消"){ _, _-> }
                show()
            }
        }
    }
}