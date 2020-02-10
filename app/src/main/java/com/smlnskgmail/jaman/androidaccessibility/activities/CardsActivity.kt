package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.adapters.CardListAdapter
import com.smlnskgmail.jaman.androidaccessibility.models.CardItem
import com.smlnskgmail.jaman.androidaccessibility.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_cards.*

class CardsActivity : BaseActivity(), CardListAdapter.ItemClickListeners {

    private var cardsListAdapter: CardListAdapter? = null
    private var cardsListLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardsListAdapter = CardListAdapter(cardsList())
        cardsListAdapter!!.setClickListener(this)
        cards_recyclerview.setHasFixedSize(true)
        cardsListLayoutManager = LinearLayoutManager(this)
        cards_recyclerview.layoutManager = cardsListLayoutManager
        cards_recyclerview.adapter = cardsListAdapter
        setUpItemTouchHelper()
    }

    private fun cardsList(): MutableList<CardItem> {
        val cardsList = arrayListOf<CardItem>()
        cardsList.add(
            CardItem(
                avatarId = R.drawable.avatar_jane_doe,
                name = "Jane Doe",
                date = CommonUtils.getNearDate(),
                country = "Brazil",
                shareText = "Beautiful place!",
                imageId = R.drawable.iguazu_falls
            )
        )
        cardsList.add(
            CardItem(
                avatarId = R.drawable.avatar_john_doe,
                name = "John Doe",
                date = CommonUtils.getNearDate(),
                country = "United States",
                shareText = "New adventures last weekend and possibly one of the most beautiful places I've been!",
                imageId = R.drawable.niagara_falls
            )
        )
        cardsList.add(
            CardItem(
                avatarId = R.drawable.avatar_rebecca_williams,
                name = "Rebecca Williams",
                date = CommonUtils.getNearDate(),
                country = "United States",
                shareText = "No words to describe this place!",
                imageId = R.drawable.yosemite
            )
        )
        return cardsList
    }

    override fun onLikeClicked(view: View?, position: Int) {
        val item: CardItem = cardsListAdapter!!.getItem(position)
        item.isLiked = !item.isLiked
        cardsListAdapter!!.notifyDataSetChanged()
    }

    override fun onCommentClicked(view: View?, position: Int) {
        Toast.makeText(
            this,
            "Comment is disabled!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onFavoriteClicked(view: View?, position: Int) {
        val item: CardItem = cardsListAdapter!!.getItem(position)
        item.isFavorite = !item.isFavorite
        cardsListAdapter!!.notifyDataSetChanged()
    }

    override fun onShareClicked(view: View?, position: Int) {
        val shareBody: String = cardsListAdapter!!.getItem(position).shareText
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        val chooserTitle = resources.getString(R.string.cards_share_via)
        startActivity(
            Intent.createChooser(
                shareIntent,
                chooserTitle
            )
        )
    }

    override fun onMoreOptionsClicked(view: View?, position: Int) {
        val popup = PopupMenu(this, view!!)
        popup.menuInflater.inflate(
            R.menu.cards_card_more_options_menu,
            popup.menu
        )
        popup.setOnMenuItemClickListener {
            cardsListAdapter!!.removeItem(position)
            true
        }
        popup.show()
    }

    private fun setUpItemTouchHelper() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                var background: Drawable? = null
                var archiveIcon: Drawable? = null
                var initiated = false

                private fun init() {
                    background = ColorDrawable(Color.RED)
                    archiveIcon = ContextCompat.getDrawable(
                        baseContext,
                        R.drawable.ic_archive_24dp
                    )
                    initiated = true
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    swipeDir: Int
                ) {
                    val position: Int = viewHolder.adapterPosition
                    val adapter: CardListAdapter = cards_recyclerview.adapter as CardListAdapter
                    adapter.removeItem(position)
                }

                override fun onChildDraw(
                    canvas: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val itemView: View = viewHolder.itemView
                    if (viewHolder.adapterPosition == -1) {
                        return
                    }
                    if (!initiated) {
                        init()
                    }
                    // draw red background
                    background!!.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    background!!.draw(canvas)
                    val itemHeight = itemView.bottom - itemView.top
                    val intrinsicWidth = archiveIcon!!.intrinsicWidth
                    val intrinsicHeight = archiveIcon!!.intrinsicWidth
                    val left = itemView.right - intrinsicWidth
                    val right = itemView.right
                    val top = itemView.top + (itemHeight - intrinsicHeight) / 2
                    val bottom = top + intrinsicHeight
                    archiveIcon!!.setBounds(left, top, right, bottom)
                    archiveIcon!!.draw(canvas)
                    super.onChildDraw(
                        canvas,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }

            }
        val itemTouchHelper = ItemTouchHelper(
            simpleItemTouchCallback
        )
        itemTouchHelper.attachToRecyclerView(cards_recyclerview)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_cards
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, CardsActivity::class.java)
        }

    }

}
