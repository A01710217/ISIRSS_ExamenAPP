package com.example.isirss_examenapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PersonajesObject(
    @SerializedName("items") val items: ArrayList<PersonajesBase>,
    @SerializedName("limit") val limit: Int,
)
