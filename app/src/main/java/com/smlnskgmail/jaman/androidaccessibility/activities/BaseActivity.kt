package com.smlnskgmail.jaman.androidaccessibility.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.smlnskgmail.jaman.androidaccessibility.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            toolbar.touchscreenBlocksFocus = false
            supportActionBar?.setDisplayHomeAsUpEnabled(
                isUpEnabled()
            )
        }
    }

    protected abstract fun getLayoutResId(): Int

    protected open fun isUpEnabled(): Boolean {
        return true
    }

}
