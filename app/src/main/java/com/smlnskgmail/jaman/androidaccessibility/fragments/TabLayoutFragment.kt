package com.smlnskgmail.jaman.androidaccessibility.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.androidaccessibility.R

class TabLayoutFragment : Fragment() {

    companion object {

        private val TEXT_KEY = "text_key"
        private var text: String? = null

        fun newInstance(text: String?): TabLayoutFragment {
            val args = Bundle()
            val fragment = TabLayoutFragment()
            args.putString(TEXT_KEY, text)
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            text = arguments!!.getString(TEXT_KEY)
        }
    }

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_tab_layout, container, false)
        val textView = v.findViewById<TextView>(R.id.tab_layout_textview)
        textView.text = Html.fromHtml(text)
        textView.movementMethod = ScrollingMovementMethod()
        return v
    }

}