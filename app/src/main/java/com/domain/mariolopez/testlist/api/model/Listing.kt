package com.domain.mariolopez.testlist.api.model

import com.google.gson.annotations.SerializedName

class Listing {

    @SerializedName("AdId")
    val adId: String = ""
    @SerializedName("AgencyLogoUrl")
    val agencyLogoUrl: String = ""

    @SerializedName("Bathrooms")
    val bathrooms: Int = 0

    @SerializedName("Bedrooms")
    val bedrooms: Int = 0

    @SerializedName("Carspaces")
    val carspaces: Int = 0

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

    val isEliteBoolean: Boolean
        get() = isEliteNum.binaryIntToBoolean()

}

fun Int.binaryIntToBoolean(): Boolean = this == 1
