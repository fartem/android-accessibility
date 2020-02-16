package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import com.smlnskgmail.jaman.androidaccessibility.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login_login_button.setOnClickListener(this)

        ViewCompat.setAccessibilityLiveRegion(
            login_error_message,
            ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE
        )
    }

    override fun onClick(v: View) {
        if (!hasEmptyFields()) {
            login_error_message!!.text = getString(R.string.login_invalid)
            login_email_address.error = null
            login_password.error = null
        }
    }

    private fun hasEmptyFields(): Boolean {
        var hasEmptyFields = false
        if (login_email_address!!.editText!!.text.isEmpty()) {
            login_email_address!!.error = getString(R.string.login_email_required)
            hasEmptyFields = true
        }
        if (login_password.editText!!.text.isEmpty()) {
            login_password!!.error = getString(R.string.login_password_required)
            hasEmptyFields = true
        }
        return hasEmptyFields
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(context, LoginActivity::class.java)
        }

    }

}
