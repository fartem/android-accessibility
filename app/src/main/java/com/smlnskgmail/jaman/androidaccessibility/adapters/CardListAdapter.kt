package com.smlnskgmail.jaman.androidaccessibility.adapters

import android.graphics.Color
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.models.CardItem
import kotlinx.android.synthetic.main.card_list_item.view.*
import java.util.*

class CardListAdapter(
    private val cardData: MutableList<CardItem>
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {

    private var clickListeners: ItemClickListeners? = null

    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.card_list_item,
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

    fun getItem(position: Int): CardItem {
        return cardData[position]
    }

    fun removeItem(position: Int) {
        cardData.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setClickListener(itemClickListeners: ItemClickListeners?) {
        clickListeners = itemClickListeners
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

    interface ItemClickListeners {

        fun onLikeClicked(view: View?, position: Int)
        fun onCommentClicked(view: View?, position: Int)
        fun onFavoriteClicked(view: View?, position: Int)
        fun onShareClicked(view: View?, position: Int)
        fun onMoreOptionsClicked(view: View?, position: Int)

    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.cards_card_like.setOnClickListener(this)
            itemView.cards_card_comment.setOnClickListener(this)
            itemView.cards_card_favorite.setOnClickListener(this)
            itemView.cards_card_share.setOnClickListener(this)
            itemView.cards_card_more_options.setOnClickListener(this)
        }

        fun bind(position: Int) {
            val item: CardItem = getItem(position)
            itemView.cards_card_avatar.setImageResource(item.avatarId)
            itemView.cards_card_name.text = item.name

            val `when` = item.date.time
            val now = Calendar.getInstance().timeInMillis
            val time = DateUtils.getRelativeTimeSpanString(
                `when`,
                now,
                DateUtils.HOUR_IN_MILLIS
            )
            itemView.cards_card_timeloc.text = String.format(
                itemView.resources.getString(R.string.cards_timeloc_format),
                time,
                item.country
            )
            itemView.cards_card_share_text.text = item.shareText
            itemView.cards_card_image.setImageResource(item.imageId)
            if (item.isLiked) {
                itemView.cards_card_like.setColorFilter(Color.BLUE)
                itemView.cards_card_like.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_thumb_up_24dp
                    )
                )
            } else {
                itemView.cards_card_like.colorFilter = null
                itemView.cards_card_like.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_thumb_up_border_24dp
                    )
                )
            }
            if (item.isFavorite) {
                itemView.cards_card_favorite.setColorFilter(Color.RED)
                itemView.cards_card_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite_24dp
                    )
                )
            } else {
                itemView.cards_card_favorite.colorFilter = null
                itemView.cards_card_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite_border_24dp
                    )
                )
            }
        }

        override fun onClick(view: View) {
            val position: Int = adapterPosition
            when (view.id) {
                R.id.cards_card_like -> if (clickListeners != null) {
                    clickListeners!!.onLikeClicked(view, position)
                }
                R.id.cards_card_comment -> if (clickListeners != null) {
                    clickListeners!!.onCommentClicked(view, position)
                }
                R.id.cards_card_favorite -> if (clickListeners != null) {
                    clickListeners!!.onFavoriteClicked(view, position)
                }
                R.id.cards_card_share -> if (clickListeners != null) {
                    clickListeners!!.onShareClicked(view, position)
                }
                R.id.cards_card_more_options -> if (clickListeners != null) {
                    clickListeners!!.onMoreOptionsClicked(view, position)
                }
            }
        }

    }

}
