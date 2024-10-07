package com.example.isirss_examenapp.data.network

import com.example.isirss_examenapp.data.network.model.PersonajesObject
import com.example.isirss_examenapp.data.network.model.PlanetasObject
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonBallAPIService {
    //https://dragonball-api.com/api/characters?limit=paginacion
    @GET("characters")
    //suspend sirve para que la funcion sea asincrona
    suspend fun getPersonajesList(
        @Query("limit") limit:Int
    ): PersonajesObject //Metodo Post para obtener la lista de personajes

    //https://dragonball-api.com/api/planets?limit=paginacion
    @GET("planets")
    //suspend sirve para que la funcion sea asincrona
    suspend fun getPlanetasList(
        @Query("limit") limit:Int
    ): PlanetasObject //Metodo Post para obtener la lista de planetas
}