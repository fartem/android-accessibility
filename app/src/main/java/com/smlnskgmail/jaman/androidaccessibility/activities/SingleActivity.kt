package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.fragments.DetailFragment
import com.smlnskgmail.jaman.androidaccessibility.fragments.ListFragment

class SingleActivity : BaseActivity(), ListFragment.ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment: ListFragment = ListFragment.newInstance()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.single_fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onListItemClicked(view: View?, position: Int) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
            .add(R.id.single_fragment_container, DetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_single
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(context, SingleActivity::class.java)
        }

    }

}
