package com.smlnskgmail.jaman.androidaccessibility.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R

class ListAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var clickListener: ItemClickListener? = null

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.simple_list_item, parent, false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        @NonNull holder: ViewHolder,
        position: Int
    ) {
        holder.mTextView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemClickListener {
        fun onItemClicked(view: View?, position: Int)
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var mTextView: TextView = itemView.findViewById(R.id.simple_list_item_textview)

        init {
            mTextView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (clickListener == null) return
            val position = adapterPosition
            clickListener!!.onItemClicked(view, position)
        }

    }

}