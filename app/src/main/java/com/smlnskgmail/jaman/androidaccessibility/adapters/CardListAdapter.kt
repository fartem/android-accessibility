package com.smlnskgmail.jaman.androidaccessibility.adapters

import android.graphics.Color
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.models.CardItem
import kotlinx.android.synthetic.main.card_list_item.view.*
import java.util.*

class CardListAdapter(
    private val cardData: MutableList<CardItem>
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {

    private var clickListeners: ItemClickListeners? = null

    init {
        setHasStableIds(true)
    }

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

    override fun getItemId(position: Int): Long {
        return cardData[position].avatarId.toLong()
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

//            disableAccessibilityFor(itemView.cards_card_like)
//            disableAccessibilityFor(itemView.cards_card_comment)
//            disableAccessibilityFor(itemView.cards_card_favorite)
//            disableAccessibilityFor(itemView.cards_card_share)
//            disableAccessibilityFor(itemView.cards_card_more_options)
        }

        @Suppress("unused")
        private fun disableAccessibilityFor(view: View) {
            ViewCompat.setImportantForAccessibility(
                view,
                ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO
            )
            view.contentDescription = null
        }

        fun bind(position: Int) {
            val item: CardItem = getItem(position)
            itemView.cards_card_avatar.setImageResource(item.avatarId)
            itemView.cards_card_name.text = item.name

            val name = item.name

            itemView.cards_card_more_options.contentDescription = contentDescriptionForName(
                R.string.cards_card_more_options_button,
                name
            )

            val commentDescription = contentDescriptionForName(
                R.string.cards_card_comment_button,
                name
            )
            itemView.cards_card_comment.contentDescription = commentDescription

            val shareDescription = contentDescriptionForName(
                R.string.cards_card_share_button,
                name
            )
            itemView.cards_card_share.contentDescription = shareDescription

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

            val likeDescription: String
            if (item.isLiked) {
                itemView.cards_card_like.setColorFilter(Color.BLUE)
                itemView.cards_card_like.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_thumb_up_24dp
                    )
                )
                likeDescription = contentDescriptionForName(
                    R.string.cards_card_unlike_button,
                    name
                )
            } else {
                itemView.cards_card_like.colorFilter = null
                itemView.cards_card_like.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_thumb_up_border_24dp
                    )
                )
                likeDescription = contentDescriptionForName(
                    R.string.cards_card_like_button,
                    name
                )
            }
            itemView.cards_card_like.contentDescription = likeDescription

            val favoriteDescription: String
            if (item.isFavorite) {
                itemView.cards_card_favorite.setColorFilter(Color.RED)
                itemView.cards_card_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite_24dp
                    )
                )
                favoriteDescription = contentDescriptionForName(
                    R.string.cards_card_unfavorite_button,
                    name
                )
            } else {
                itemView.cards_card_favorite.colorFilter = null
                itemView.cards_card_favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_favorite_border_24dp
                    )
                )
                favoriteDescription = contentDescriptionForName(
                    R.string.cards_card_favorite_button,
                    name
                )
            }
            itemView.cards_card_favorite.contentDescription = favoriteDescription

//            ViewCompat.setAccessibilityDelegate(
//                itemView,
//                object : AccessibilityDelegateCompat() {
//                    override fun onInitializeAccessibilityNodeInfo(
//                        host: View?,
//                        info: AccessibilityNodeInfoCompat?
//                    ) {
//                        super.onInitializeAccessibilityNodeInfo(host, info)
//
//                        info!!.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.id,
//                                "View full post"
//                            )
//                        )
//
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_like,
//                                likeDescription
//                            )
//                        )
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_comment,
//                                commentDescription
//                            )
//                        )
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_favorite,
//                                favoriteDescription
//                            )
//                        )
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_share,
//                                shareDescription
//                            )
//                        )
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_archive,
//                                itemView.context.getString(
//                                    R.string.cards_card_archive_this_post
//                                )
//                            )
//                        )
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_remove,
//                                itemView.context.getString(
//                                    R.string.cards_card_remove_this_post
//                                )
//                            )
//                        )
//                        info.addAction(
//                            AccessibilityNodeInfoCompat.AccessibilityActionCompat(
//                                R.id.action_card_report,
//                                itemView.context.getString(
//                                    R.string.cards_card_report_this_post
//                                )
//                            )
//                        )
//                    }
//
//                    override fun performAccessibilityAction(
//                        host: View?,
//                        action: Int,
//                        args: Bundle?
//                    ): Boolean {
//                        when (action) {
//                            R.id.action_card_like -> {
//                                onClick(itemView.cards_card_like)
//                                return true
//                            }
//                            R.id.action_card_comment -> {
//                                onClick(itemView.cards_card_comment)
//                                return true
//                            }
//                            R.id.action_card_favorite -> {
//                                onClick(itemView.cards_card_favorite)
//                                return true
//                            }
//                            R.id.action_card_share -> {
//                                onClick(itemView.cards_card_share)
//                                return true
//                            }
//                            R.id.action_card_archive,
//                            R.id.action_card_remove,
//                            R.id.action_card_report -> {
//                                removeItem(position)
//                                return true
//                            }
//                        }
//                        return super.performAccessibilityAction(host, action, args)
//                    }
//                }
//            )
        }

        private fun contentDescriptionForName(
            descriptionResId: Int,
            name: String
        ): String {
            return itemView.context.getString(
                descriptionResId
            ).format(name)
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
