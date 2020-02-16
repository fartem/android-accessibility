package com.smlnskgmail.jaman.androidaccessibility.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.smlnskgmail.jaman.androidaccessibility.R
import kotlinx.android.synthetic.main.fragment_tab_layout.view.*

class TabLayoutFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            view.tab_layout_textview.text = Html.fromHtml(
                arguments!!.getString(TEXT_KEY)
            )
            view.tab_layout_textview.movementMethod = ScrollingMovementMethod()
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_tab_layout
    }

    companion object {

        private const val TEXT_KEY = "text_key"

        fun newInstance(text: String?): TabLayoutFragment {
            val args = Bundle()
            val fragment = TabLayoutFragment()
            args.putString(TEXT_KEY, text)
            fragment.arguments = args
            return fragment
        }

    }

}