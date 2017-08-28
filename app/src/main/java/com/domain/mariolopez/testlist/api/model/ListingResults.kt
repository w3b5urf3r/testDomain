package com.domain.mariolopez.testlist.api.model

import com.google.gson.annotations.SerializedName

class ListingResults {

    @SerializedName("Listings")
    val listings : List<Listing> = emptyList()
}