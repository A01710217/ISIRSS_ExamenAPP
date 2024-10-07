package com.example.isirss_examenapp.domain

import com.example.isirss_examenapp.data.PersonajesRepository
import com.example.isirss_examenapp.data.network.model.PersonajesObject

class PersonajesListRequirement {
    private val personajesRepository = PersonajesRepository()

    suspend operator fun invoke(
        page:Int,
        limit:Int
    ): PersonajesObject? = personajesRepository.getPersonajesList(page, limit)
}