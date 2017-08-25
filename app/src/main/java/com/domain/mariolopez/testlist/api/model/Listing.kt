package com.domain.mariolopez.testlist.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by mariolopez on 25/8/17.
 */

class Listing {

    @SerializedName("AgencyLogoUrl")
    val agencyLogoUrl: String = ""

    @SerializedName("Bathrooms")
    val bathrooms: Int =0

    @SerializedName("Bedrooms")
    val bedrooms: String = ""

    @SerializedName("Carspaces")
    val carspaces: String = ""

    @SerializedName("DisplayPrice")
    val displayPrice: String = ""

    @SerializedName("DisplayableAddress")
    val DisplayableAddress: String = ""

    @SerializedName("TruncatedDescription")
    val truncatedDescription: String = ""

    @SerializedName("RetinaDisplayThumbUrl")
    val RetinaDisplayThumbUrl: String = ""

    @SerializedName("SecondRetinaDisplayThumbUrl")
    val SecondRetinaDisplayThumbUrl: String = ""

    @SerializedName("IsElite")
    val IsElite: String = ""


}