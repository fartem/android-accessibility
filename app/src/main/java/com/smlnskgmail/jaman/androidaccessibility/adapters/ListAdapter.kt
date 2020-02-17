package com.smlnskgmail.jaman.androidaccessibility.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R
import kotlinx.android.synthetic.main.simple_list_item.view.*

class ListAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var clickListener: ItemClickListener? = null

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.simple_list_item,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        @NonNull holder: ViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemClickListener {
        fun onItemClicked(view: View?, position: Int)
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.simple_list_item_textview.setOnClickListener(this)
        }

        fun bind(position: Int) {
            itemView.simple_list_item_textview.text = data[position]
        }

        override fun onClick(view: View) {
            if (clickListener == null) {
                return
            }
            val position = adapterPosition
            clickListener!!.onItemClicked(view, position)
        }

    }

}
