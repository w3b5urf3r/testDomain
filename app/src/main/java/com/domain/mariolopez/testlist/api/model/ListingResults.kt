package com.domain.mariolopez.testlist.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by mariolopez on 25/8/17.
 */
class ListingResults {

    @SerializedName("Listings")
    val listings : List<Listing> = emptyList()
}