package com.domain.mariolopez.testlist.ui.adapter

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.domain.mariolopez.testlist.R
import com.domain.mariolopez.testlist.api.model.Listing
import com.domain.mariolopez.testlist.api.model.binaryIntToBoolean
import com.domain.mariolopez.testlist.ui.ViewAnkoComponent
import com.domain.mariolopez.testlist.ui.layouts.ListingEliteItemUI
import com.domain.mariolopez.testlist.ui.layouts.ListingItemUI

class ListingsAdapter : BaseAdapter<Listing>() {


    override val bind: ViewAnkoComponent<*>.(listing: Listing) -> Unit = { listing ->
        if (listing.isEliteBoolean) {
            this as ListingEliteItemUI
            setFieldTextViews(price, rooms, address, listing)

            Glide.with(address.context)
                    .load(listing.retinaDisplayThumbUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ph_loading)
                    .into(houseImage)

            Glide.with(address.context)
                    .load(listing.secondRetinaDisplayThumbUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ph_loading)
                    .into(houseImage2)
        } else {
            this as ListingItemUI
            setFieldTextViews(price, rooms, address, listing)
            Glide.with(address.context)
                    .load(listing.retinaDisplayThumbUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ph_loading)
                    .into(houseImage)
        }

    }

    private fun setFieldTextViews(price: TextView, rooms: TextView, address: TextView, listing: Listing) {
        price.text = listing.displayPrice
        rooms.text = listing.bedrooms.toString()
        address.text = listing.displayableAddress
    }

    override fun onCreateComponent(parent: RecyclerView, viewType: Int): ViewAnkoComponent<RecyclerView> {

        val isElite = viewType.binaryIntToBoolean()
        if (isElite) {
            return ListingEliteItemUI(parent)
        } else {
            return ListingItemUI(parent)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return items[position].isEliteNum
    }


}
