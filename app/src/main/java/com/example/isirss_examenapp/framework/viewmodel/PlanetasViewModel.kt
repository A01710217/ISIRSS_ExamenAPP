package com.example.isirss_examenapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.data.network.model.PlanetasBase
import com.example.isirss_examenapp.data.network.model.PlanetasObject
import com.example.isirss_examenapp.domain.PlanetasListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetasViewModel : ViewModel() {

    val planetasObjectLiveData = MutableLiveData<PlanetasObject>()
    private val planetasListRequirement = ArrayList<PlanetasBase>()
    private var currentPage = 1
    private val limit = 20

    fun getPlanetasList(loadMore: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: PlanetasObject? = PlanetasListRequirement()(currentPage, limit)
            Log.d("PlanetasViewModel", "Planetas: $result")

            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    if (!loadMore) {
                        planetasListRequirement.clear() // Limpia la lista existente
                    }
                    planetasListRequirement.addAll(result.items) // Agrega los nuevos resultados
                    planetasObjectLiveData.postValue(result!!) // Actualiza la lista en el LiveData
                    currentPage++ // Incrementa la página para la siguiente carga
                    Log.d("PlanetasViewModel", "Pagina: ${currentPage}")
                } else {
                    Log.e("PlanetasViewModel", "El resultado es nulo")
                }
            }
        }
    }
}