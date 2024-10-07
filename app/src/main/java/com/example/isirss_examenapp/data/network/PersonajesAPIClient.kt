package com.example.isirss_examenapp.data.network

class PersonajesAPIClient {
    private lateinit var api: DragonBallAPIService

    suspend fun getPersonajesList(limit : Int): com.example.isirss_examenapp.data.network.model.PersonajesObject?{
        api = PersonajeNetworkModuleDI()
        return try{
            api.getPersonajesList(limit)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }
}