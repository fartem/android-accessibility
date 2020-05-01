package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.adapters.TabLayoutPagerAdapter
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPagerAdapter = TabLayoutPagerAdapter(
            supportFragmentManager,
            resources.getStringArray(
                R.array.tab_layout_tabs_content
            )
        )
        tab_layout_view_pager.adapter = viewPagerAdapter
        tab_layout_tabs.setupWithViewPager(
            tab_layout_view_pager
        )

        val tabNames = resources.getStringArray(
            R.array.tab_layout_tabs
        )
        for (i in 0 until tab_layout_tabs.tabCount) {
            val tab = tab_layout_tabs.getTabAt(i)
            if (tab != null && tabNames[i] != null) {
                tab.text = tabNames[i]

                val contentDescription = getString(
                    R.string.tab_layout_content_description
                ).format(i + 1, tab_layout_tabs.tabCount)
                tab.contentDescription = tabNames[i] + ", " + contentDescription
            }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_tab_layout
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(
                context,
                TabLayoutActivity::class.java
            )
        }

    }

}
