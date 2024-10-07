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

    private val personajesList = ArrayList<PersonajesBase>()
    private var currentPage = 1
    private val limit = 20

    // Método para obtener la lista de personajes con paginación
    fun getPersonajesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result: PersonajesObject? = personajesListRequirement(currentPage, limit)
            Log.d("PersonajesViewModel", "Personajes: $result")
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    personajesList.clear() // Limpia la lista existente
                    personajesList.addAll(result.items) // Agrega los nuevos resultados
                    personajesObjectLiveData.postValue(result!!) // Actualiza la lista en el LiveData
                    currentPage++ // Incrementa la página para la siguiente carga
                    Log.d("PersonajesViewModel", "Pagina: ${currentPage}")
                } else {
                    Log.e("PersonajesViewModel", "El resultado es nulo")
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
