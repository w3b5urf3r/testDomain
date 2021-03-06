package com.domain.mariolopez.testlist.ui.layouts

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.domain.mariolopez.testlist.R
import com.domain.mariolopez.testlist.ui.ViewAnkoComponent
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ListingEliteItemUI(override val view: RecyclerView) : ViewAnkoComponent<RecyclerView> {


    lateinit var houseImage: ImageView
    lateinit var houseImage2: ImageView
    lateinit var agencyLogo: ImageView

    lateinit var price: TextView
    lateinit var rooms: TextView
    lateinit var address: TextView

    @SuppressLint("PrivateResource")
    override fun createView(ui: AnkoContext<RecyclerView>) = with(ui) {

        cardView {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            cardElevation = 10F
            preventCornerOverlap = true
            useCompatPadding = true

            verticalLayout {
                backgroundResource = R.drawable.bg_card
                padding = dimen(R.dimen.abc_list_item_padding_horizontal_material)
                relativeLayout {
                    space {
                        id = R.id.space_view
                    }.lparams(width = dip(5)) {
                            centerInParent()

                    }
                    houseImage2 = imageView().lparams(matchParent, matchParent) {
                        height = dip(150)
                        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            alignParentStart()
                        } else {
                            alignParentLeft()
                        }
                        leftOf(R.id.space_view)
                    }
                    houseImage = imageView().lparams(matchParent, matchParent) {
                        height = dip(150)
                        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            alignParentEnd()
                        } else {
                            alignParentRight()
                        }
                        rightOf(R.id.space_view)
                    }
                }.lparams(matchParent, wrapContent)

                verticalLayout {
                    price = priceTextView()

                    rooms = roomsTextView()

                    address = addressTextView()

                    agencyLogo = logoImageView()

                }.lparams(height = wrapContent, width = matchParent)
                        .applyRecursively { view ->
                            when (view) {
                                is TextView -> {
                                    view.ellipsize = TextUtils.TruncateAt.END
                                }

                            }
                        }
            }
        }
    }
}


