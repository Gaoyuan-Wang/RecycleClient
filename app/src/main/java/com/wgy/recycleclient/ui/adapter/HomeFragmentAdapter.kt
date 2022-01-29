package com.wgy.recycleclient.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class HomeFragmentAdapter(fragmentManager: FragmentManager, fragments: List<Fragment>?): FragmentPagerAdapter(fragmentManager) {
    private var fragmentList: List<Fragment>? = null

    init{
        fragmentList = fragments
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }
}