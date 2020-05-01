package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import com.smlnskgmail.jaman.androidaccessibility.R

class CustomViewActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_custom_view
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(
                context,
                CustomViewActivity::class.java
            )
        }

    }

}
