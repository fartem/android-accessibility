package com.smlnskgmail.jaman.androidaccessibility.activities

import android.os.Bundle
import android.widget.Button
import com.smlnskgmail.jaman.androidaccessibility.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main_login_button.setOnClickListener {
            startActivity(
                LoginActivity.newIntent(this)
            )
        }
        main_settings_button.setOnClickListener {
            startActivity(
                SettingsActivity.newIntent(this)
            )
        }
        main_tablayout_button.setOnClickListener {
            startActivity(
                TabLayoutActivity.newIntent(this)
            )
        }
        main_single_button.setOnClickListener {
            startActivity(
                SingleActivity.newIntent(this)
            )
        }
        main_cards_button.setOnClickListener {
            startActivity(
                CardsActivity.newIntent(this)
            )
        }
        main_custom_button.setOnClickListener {
            startActivity(
                CustomViewActivity.newIntent(this)
            )
        }
        main_charts_button.setOnClickListener {
            startActivity(
                ChartsActivity.newIntent(this)
            )
        }
    }

    override fun isUpEnabled(): Boolean {
        return false
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

}
