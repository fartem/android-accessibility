package com.smlnskgmail.jaman.androidaccessibility.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.smlnskgmail.jaman.androidaccessibility.fragments.TabLayoutFragment

class TabLayoutPagerAdapter(
    fragmentManager: FragmentManager?,
    private val mData: Array<String>
) : FragmentStatePagerAdapter(fragmentManager!!) {

    override fun getItem(position: Int): Fragment {
        return TabLayoutFragment.newInstance(mData[position])
    }

    override fun getCount(): Int {
        return mData.size
    }

    companion object {

        const val count = 3

    }

}