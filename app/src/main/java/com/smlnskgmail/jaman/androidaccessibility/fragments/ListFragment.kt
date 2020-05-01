package com.smlnskgmail.jaman.androidaccessibility.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.adapters.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListFragment : BaseFragment(), ListAdapter.ItemClickListener {

    private var callback: ItemClickListener? = null

    @SuppressWarnings("SwallowedException")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = try {
            context as ItemClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$context must implement OnHeadlineSelectedListener"
            )
        }
    }

    @SuppressWarnings("MagicNumber")
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val listItems: MutableList<String> = ArrayList()
        for (i in 0..19) {
            listItems.add("Option $i")
        }
        val adapter = ListAdapter(listItems)
        adapter.clickListener = this

        single_list_recyclerview.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        single_list_recyclerview.layoutManager = layoutManager
        single_list_recyclerview.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            single_list_recyclerview.context,
            layoutManager.orientation
        )
        single_list_recyclerview.addItemDecoration(
            dividerItemDecoration
        )
    }

    override fun onItemClicked(
        view: View?,
        position: Int
    ) {
        callback!!.onListItemClicked(
            view,
            position
        )
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_list
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }

    }

    interface ItemClickListener {

        fun onListItemClicked(
            view: View?,
            position: Int
        )

    }

}
