package com.example.isirss_examenapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PlanetasBase(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
)
