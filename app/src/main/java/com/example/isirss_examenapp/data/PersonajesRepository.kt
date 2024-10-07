package com.example.isirss_examenapp.data

import com.example.isirss_examenapp.data.network.PersonajesAPIClient
import com.example.isirss_examenapp.data.network.model.PersonajesObject

class PersonajesRepository {
    private val apiDragonBall = PersonajesAPIClient()

    suspend fun getPersonajesList(limit : Int): PersonajesObject? = apiDragonBall.getPersonajesList(limit)
}