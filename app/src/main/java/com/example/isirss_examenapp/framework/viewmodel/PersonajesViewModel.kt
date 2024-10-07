package com.example.isirss_examenapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.data.network.model.PersonajesObject
import com.example.isirss_examenapp.domain.BuscarPersonajesrequirement
import com.example.isirss_examenapp.domain.PersonajesListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajesViewModel : ViewModel() {

    val personajesObjectLiveData = MutableLiveData<PersonajesObject>()
    val personajesSearchResultLiveData = MutableLiveData<List<PersonajesBase>>() // Para la búsqueda

    private val personajesListRequirement = PersonajesListRequirement()
    private val buscarPersonajeRequirement = BuscarPersonajesrequirement()

    // Método para obtener la lista de personajes con límite
    fun getPersonajesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result: PersonajesObject? = personajesListRequirement(10)
            Log.d("PersonajesViewModel", "Personajes: $result")
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    personajesObjectLiveData.postValue(result!!)
                } else {
                    Log.e("PersonajesViewModel", "El resultado es nulo")
                    // Manejo del caso nulo
                }
            }
        }
    }

    // Método para buscar personajes por nombre
    fun searchPersonajeByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = buscarPersonajeRequirement(name) // Aquí se usa invoke
            Log.d("PersonajesViewModel", "Resultado de búsqueda: $result")
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    personajesSearchResultLiveData.postValue(result!!)
                } else {
                    Log.e("PersonajesViewModel", "No se encontró ningún personaje con ese nombre")
                }
            }
        }
    }

}
