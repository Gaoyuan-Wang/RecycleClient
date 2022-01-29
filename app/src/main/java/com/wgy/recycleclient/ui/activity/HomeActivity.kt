package com.wgy.recycleclient.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.wgy.recycleclient.BaseActivity
import com.wgy.recycleclient.ui.adapter.HomeFragmentAdapter
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.viewmodel.HomeViewModel
import com.wgy.recycleclient.ui.fragment.ActivityFragment
import com.wgy.recycleclient.ui.fragment.IntegralFragment
import com.wgy.recycleclient.ui.fragment.PersonalFragment
import com.wgy.recycleclient.ui.fragment.RecycleFragment
import kotlinx.android.synthetic.main.home_layout.*

@RequiresApi(Build.VERSION_CODES.N)
class HomeActivity : BaseActivity() {
    //加载ViewModel
    private val viewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        initData()

        }

    //初始化底部导航栏
    private fun initData(){
        val mainFragmentList:ArrayList<Fragment> = ArrayList()
        mainFragmentList.add(RecycleFragment())
        mainFragmentList.add(ActivityFragment())
        mainFragmentList.add(IntegralFragment())
        mainFragmentList.add(PersonalFragment())

        val mainFragmentAdapter = HomeFragmentAdapter(supportFragmentManager, mainFragmentList)
        viewpager.adapter = mainFragmentAdapter
        /**
        recycle为回收页面
        activity为活动页面
        integral为积分页面
        personal为个人页面
        **/
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.recycle -> viewpager.currentItem = 0
                R.id.activity -> viewpager.currentItem = 1
                R.id.integral -> viewpager.currentItem = 2
                R.id.personal -> viewpager.currentItem = 3
            }
            false
        }

        viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                //滑动到某页面让底部导航栏为选中状态
                bottom_navigation.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })

        //禁止ViewPager滑动
        viewpager.setOnTouchListener { _, _ -> true }
    }
}