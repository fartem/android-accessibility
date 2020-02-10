package com.smlnskgmail.jaman.androidaccessibility.fragments

import com.smlnskgmail.jaman.androidaccessibility.R

class DetailFragment : BaseFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_detail
    }

    companion object {

        fun newInstance(): DetailFragment {
            return DetailFragment()
        }

    }

}