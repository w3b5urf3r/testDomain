package com.domain.mariolopez.testlist.ui.adapter

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
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
            setCommonFields(price, rooms, address, houseImage, agencyLogo, listing)

            Glide.with(address.context)
                    .load(listing.secondRetinaDisplayThumbUrl)
                    .centerCrop() //not using fit center as some images have a different aspect ratio
                    .placeholder(R.drawable.ph_loading)
                    .into(houseImage2)

        } else {
            this as ListingItemUI
            setCommonFields(price, rooms, address, houseImage, agencyLogo, listing)
        }

    }

    private fun setCommonFields(price: TextView, rooms: TextView, address: TextView,
                                houseImage: ImageView, agencyLogo: ImageView, listing: Listing) {

        price.text = listing.displayPrice
        rooms.text = rooms.context.getString(R.string.format_rooms)
                .format(listing.bedrooms, listing.bathrooms, listing.carspaces)
        address.text = listing.displayableAddress

        Glide.with(address.context)
                .load(listing.retinaDisplayThumbUrl)
                .centerCrop()
                .placeholder(R.drawable.ph_loading)
                .into(houseImage)

        Glide.with(address.context)
                .load(listing.agencyLogoUrl)
                .fitCenter()
                .placeholder(R.drawable.ph_loading)
                .into(agencyLogo)
    }

    override fun onCreateComponent(parent: RecyclerView, viewType: Int): ViewAnkoComponent<RecyclerView> {

        val isElite = viewType.binaryIntToBoolean()
        return if (isElite) {
            ListingEliteItemUI(parent)
        } else {
            ListingItemUI(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].isEliteNum
    }


}
