package com.example.isirss_examenapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PlanetasObject(
    @SerializedName("items") val items: ArrayList<PlanetasBase>,
    @SerializedName("limit") val limit: Int,
)
