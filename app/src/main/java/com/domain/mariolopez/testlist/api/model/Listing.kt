package com.domain.mariolopez.testlist.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by mariolopez on 25/8/17.
 */

class Listing {

    @SerializedName("AgencyLogoUrl")
    val agencyLogoUrl: String = ""

    @SerializedName("Bathrooms")
    val bathrooms: Int = 0

    @SerializedName("Bedrooms")
    val bedrooms: String = ""

    @SerializedName("Carspaces")
    val carspaces: String = ""

    @SerializedName("DisplayPrice")
    val displayPrice: String = ""

    @SerializedName("DisplayableAddress")
    val displayableAddress: String = ""

    @SerializedName("TruncatedDescription")
    val truncatedDescription: String = ""

    @SerializedName("RetinaDisplayThumbUrl")
    val retinaDisplayThumbUrl: String = ""

    @SerializedName("SecondRetinaDisplayThumbUrl")
    val secondRetinaDisplayThumbUrl: String = ""

    @SerializedName("IsElite")
    val isEliteNum: Int = 0
    val IsElite: Boolean = isEliteNum.intToBoolean()


}

private fun Int.intToBoolean(): Boolean = this  == 1
