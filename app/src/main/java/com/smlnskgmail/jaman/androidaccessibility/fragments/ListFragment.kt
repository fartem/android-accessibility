package com.smlnskgmail.jaman.androidaccessibility.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.adapters.ListAdapter
import java.util.*

class ListFragment : BaseFragment(), ListAdapter.ItemClickListener {

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }

    }

    var mCallback: ItemClickListener? = null

    interface ItemClickListener {
        fun onListItemClicked(view: View?, position: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = try {
            context as ItemClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement OnHeadlineSelectedListener"
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // populate some data
        val listItems: MutableList<String> = ArrayList()
        for (i in 0..19) {
            listItems.add("Option $i")
        }
        // set up adapter
        val adapter = ListAdapter(listItems)
        adapter.clickListener = this
        // set up recyclerview
        val recyclerView: RecyclerView = view.findViewById(R.id.single_list_recyclerview)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(adapter)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.getContext(),
            layoutManager.getOrientation()
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClicked(view: View?, position: Int) {
        mCallback!!.onListItemClicked(view, position)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_list
    }

}