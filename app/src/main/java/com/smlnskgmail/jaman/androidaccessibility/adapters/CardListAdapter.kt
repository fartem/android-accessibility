package com.smlnskgmail.jaman.androidaccessibility.adapters

import android.content.Context
import android.graphics.Color
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.models.CardItem
import java.util.*

class CardListAdapter(private val cardData: MutableList<CardItem>) : RecyclerView.Adapter<CardListAdapter.ViewHolder?>() {

    private var clickListeners: ItemClickListeners? = null

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.card_list_item, parent, false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        val context: Context = holder.itemView.getContext()
        val item: CardItem = getItem(position)
        holder.mAvatar.setImageResource(item.avatarId)
        holder.mName.setText(item.name)
        // Time and Location
        val `when`: Long = item.date.getTime()
        val now = Calendar.getInstance().timeInMillis
        val time =
            DateUtils.getRelativeTimeSpanString(`when`, now, DateUtils.HOUR_IN_MILLIS)
        val country: String = item.country
        val timeLocFormat = context.resources.getString(R.string.cards_timeloc_format)
        holder.mTimeLoc.text = String.format(timeLocFormat, time, country)
        holder.mShareText.setText(item.shareText)
        holder.mImage.setImageResource(item.imageId)
        if (item.isLiked) {
            holder.mLikeButton.setColorFilter(Color.BLUE)
        } else {
            holder.mLikeButton.colorFilter = null
        }
        if (item.isFavorite) {
            holder.mFavoriteButton.setColorFilter(Color.RED)
        } else {
            holder.mFavoriteButton.colorFilter = null
        }
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

    // parent activity will implement these methods for click events
    interface ItemClickListeners {
        fun onLikeClicked(view: View?, position: Int)
        fun onCommentClicked(view: View?, position: Int)
        fun onFavoriteClicked(view: View?, position: Int)
        fun onShareClicked(view: View?, position: Int)
        fun onMoreOptionsClicked(view: View?, position: Int)
    }

    /* ----------------------------------------------------------------
     * View holder
     * ---------------------------------------------------------------- */
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var mAvatar: ImageView
        var mName: TextView
        var mTimeLoc: TextView
        var mShareText: TextView
        var mImage: ImageView
        var mLikeButton: ImageButton
        var mCommentButton: ImageButton
        var mFavoriteButton: ImageButton
        var mShareButton: ImageButton
        var mMoreOptionsButton: ImageButton

        init {
            mAvatar = itemView.findViewById(R.id.cards_card_avatar)
            mName = itemView.findViewById(R.id.cards_card_name)
            mTimeLoc = itemView.findViewById(R.id.cards_card_timeloc)
            mShareText = itemView.findViewById(R.id.cards_card_share_text)
            mImage = itemView.findViewById(R.id.cards_card_image)
            mLikeButton = itemView.findViewById(R.id.cards_card_like)
            mCommentButton = itemView.findViewById(R.id.cards_card_comment)
            mFavoriteButton = itemView.findViewById(R.id.cards_card_favorite)
            mShareButton = itemView.findViewById(R.id.cards_card_share)
            mMoreOptionsButton = itemView.findViewById(R.id.cards_card_more_options)
            mLikeButton.setOnClickListener(this)
            mCommentButton.setOnClickListener(this)
            mFavoriteButton.setOnClickListener(this)
            mShareButton.setOnClickListener(this)
            mMoreOptionsButton.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position: Int = getAdapterPosition()
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
