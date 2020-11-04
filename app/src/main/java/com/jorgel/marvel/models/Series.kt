package com.jorgel.marvel.models

import com.google.gson.annotations.SerializedName

data class Series (
    @SerializedName("available") val available : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<Items>,
    @SerializedName("returned") val returned : Int
)