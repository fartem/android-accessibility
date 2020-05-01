package com.smlnskgmail.jaman.androidaccessibility.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.smlnskgmail.jaman.androidaccessibility.fragments.TabLayoutFragment

class TabLayoutPagerAdapter(
    fragmentManager: FragmentManager?,
    private val data: Array<String>
) : FragmentStatePagerAdapter(fragmentManager!!) {

    override fun getItem(position: Int): Fragment {
        return TabLayoutFragment.newInstance(
            data[position]
        )
    }

    override fun getCount(): Int {
        return data.size
    }

}
