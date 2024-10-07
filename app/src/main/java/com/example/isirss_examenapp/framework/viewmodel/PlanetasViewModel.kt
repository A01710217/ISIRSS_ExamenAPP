package com.example.isirss_examenapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isirss_examenapp.data.network.model.PersonajesObject
import com.example.isirss_examenapp.data.network.model.PlanetasObject
import com.example.isirss_examenapp.domain.PlanetasListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetasViewModel : ViewModel() {

    val planetasObjectLiveData = MutableLiveData<PlanetasObject>()
    private val planetasListRequirement = PlanetasListRequirement()

    fun getPlanetasList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result: PlanetasObject? = planetasListRequirement(10)
            Log.d("PersonajesViewModel", "Personajes: $result")
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    planetasObjectLiveData.postValue(result!!)
                } else {
                    Log.e("PersonajesViewModel", "El resultado es nulo")
                    // Aquí puedes manejar el caso de que el resultado sea nulo (mostrar un mensaje, etc.)
                }
            }
        }
    }
}