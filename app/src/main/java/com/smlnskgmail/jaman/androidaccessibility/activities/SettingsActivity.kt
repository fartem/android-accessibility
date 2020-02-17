package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.smlnskgmail.jaman.androidaccessibility.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.settings_spinner_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        settings_spinner.adapter = adapter
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_settings
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(context, SettingsActivity::class.java)
        }

    }

}
