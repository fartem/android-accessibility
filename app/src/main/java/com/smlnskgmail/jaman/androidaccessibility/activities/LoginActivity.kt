package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.smlnskgmail.jaman.androidaccessibility.R

class LoginActivity : BaseActivity(), View.OnClickListener {

    private var errorMessage: TextView? = null
    private var emailField: TextInputLayout? = null
    private var passwordField: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorMessage = findViewById(R.id.login_error_message)
        emailField = findViewById(R.id.login_email_address)
        passwordField = findViewById(R.id.login_password)
        val loginButton: Button = findViewById(R.id.login_login_button)
        loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (!hasEmptyFields()) {
            errorMessage!!.text = getString(R.string.login_invalid)
        }
    }

    private fun hasEmptyFields(): Boolean {
        var hasEmptyFields = false
        val emailEditText: EditText = emailField!!.getEditText()!!
        if (TextUtils.isEmpty(emailEditText.text)) {
            emailField!!.setError(getString(R.string.login_email_required))
            hasEmptyFields = true
        }
        val passwordEditText: EditText = passwordField!!.getEditText()!!
        if (TextUtils.isEmpty(passwordEditText.text)) {
            passwordField!!.setError(getString(R.string.login_password_required))
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
