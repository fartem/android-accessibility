package com.smlnskgmail.jaman.androidaccessibility.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab_layout.view.*

class TabLayoutFragment : Fragment() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            text = arguments!!.getString(TEXT_KEY)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.tab_layout_textview.text = Html.fromHtml(text)
        view.tab_layout_textview.movementMethod = ScrollingMovementMethod()
    }

    companion object {

        private const val TEXT_KEY = "text_key"
        private var text: String? = null

        fun newInstance(text: String?): TabLayoutFragment {
            val args = Bundle()
            val fragment = TabLayoutFragment()
            args.putString(TEXT_KEY, text)
            fragment.arguments = args
            return fragment
        }

    }

}