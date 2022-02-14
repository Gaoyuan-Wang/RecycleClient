package com.wgy.recycleclient.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wgy.recycleclient.R
import com.wgy.recycleclient.ui.activity.IntegralRecordActivity
import com.wgy.recycleclient.ui.activity.LoginActivity
import com.wgy.recycleclient.ui.activity.MailingInformationActivity
import kotlinx.android.synthetic.main.fragment_personal.*

class PersonalFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initOnClickListener()
    }

    private fun initOnClickListener(){
        exit.setOnClickListener {
            AlertDialog.Builder(activity).apply {
                setMessage("退出后不会删除任何历史数据，下次登录依然可以使用本账号")
                setCancelable(false)
                setPositiveButton("退出") { _, _ ->
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                setNegativeButton("取消") { _, _ -> }
                show()
            }
        }

        mailingInformation.setOnClickListener {
            val intent = Intent(activity, MailingInformationActivity::class.java)
            startActivity(intent)
        }

        integralRecord.setOnClickListener{
            val intent = Intent(activity,IntegralRecordActivity::class.java)
            startActivity(intent)
        }
    }
}