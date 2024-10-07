package com.example.isirss_examenapp.domain

import com.example.isirss_examenapp.data.PlanetasRepository
import com.example.isirss_examenapp.data.network.model.PlanetasObject

class PlanetasListRequirement {
    private val planetasRepository = PlanetasRepository()
    suspend operator fun invoke(
        page:Int,
        limit:Int
    ): PlanetasObject? = planetasRepository.getPlanetasList(page, limit)

}