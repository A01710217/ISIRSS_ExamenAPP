package com.example.isirss_examenapp.data

import com.example.isirss_examenapp.data.network.PlanetasAPIClient
import com.example.isirss_examenapp.data.network.model.PlanetasObject

class PlanetasRepository {
    private val apiPlanetas = PlanetasAPIClient()

    suspend fun getPlanetasList(page : Int, limit : Int): PlanetasObject? = apiPlanetas.getPlanetasList(page, limit)
}