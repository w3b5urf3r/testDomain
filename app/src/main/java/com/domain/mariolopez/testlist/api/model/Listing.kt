package com.domain.mariolopez.testlist.api.model

import com.google.gson.annotations.SerializedName

class Listing  {

    @SerializedName("AdId")
    var adId: String = ""

    @SerializedName("AgencyLogoUrl")
    var agencyLogoUrl: String = ""

    @SerializedName("Bathrooms")
    var bathrooms: Int = 0

    @SerializedName("Bedrooms")
    val bedrooms: Int = 0

    @SerializedName("Carspaces")
    var carspaces: Int = 0

    @SerializedName("DisplayPrice")
    var displayPrice: String = ""

    @SerializedName("DisplayableAddress")
    var displayableAddress: String = ""

    @SerializedName("TruncatedDescription")
    var truncatedDescription: String = ""

    @SerializedName("RetinaDisplayThumbUrl")
    var retinaDisplayThumbUrl: String = ""

    @SerializedName("SecondRetinaDisplayThumbUrl")
    var secondRetinaDisplayThumbUrl: String = ""

    @SerializedName("IsElite")
    var isEliteNum: Int = 0

    val isEliteBoolean: Boolean
        get() = isEliteNum.binaryIntToBoolean()

}


fun Int.binaryIntToBoolean(): Boolean = this == 1
