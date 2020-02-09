package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.smlnskgmail.jaman.androidaccessibility.R

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val spinner: Spinner = findViewById(R.id.settings_spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.settings_spinner_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = adapter
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