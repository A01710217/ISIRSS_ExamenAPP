package com.example.isirss_examenapp.data.network

class PlanetasAPIClient {
    private lateinit var api: DragonBallAPIService

    suspend fun getPlanetasList(page : Int, limit : Int): com.example.isirss_examenapp.data.network.model.PlanetasObject?{
        api = PlanetaNetworkModuleDI()
        return try{
            api.getPlanetasList(page, limit)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }
}