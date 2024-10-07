package com.example.isirss_examenapp.domain

import com.example.isirss_examenapp.data.PersonajesRepository

class BuscarPersonajesrequirement {
    private val repository = PersonajesRepository()

    suspend operator fun invoke(nombre: String) = repository.buscarPersonaje(nombre)
}
