package com.example.isirss_examenapp.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isirss_examenapp.data.network.model.PlanetasObject
import com.example.isirss_examenapp.domain.PlanetasListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetasViewModel : ViewModel() {

    val planetasObjectLiveData = MutableLiveData<PlanetasObject>()
    private val planetasListRequirement = PlanetasListRequirement()
    private var currentOffset = 0
    private val limit = 20

    fun getPlanetasList(loadMore: Boolean = false) {
        if (loadMore) {
            currentOffset += limit // Incrementar el offset para cargar más datos
        } else {
            currentOffset = 0 // Reiniciar el offset si es una nueva búsqueda
        }

        viewModelScope.launch(Dispatchers.IO) {
            val result: PlanetasObject? = planetasListRequirement(currentOffset, limit)
            Log.d("PlanetasViewModel", "Planetas: $result")
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    // Actualiza el LiveData con la nueva lista de planetas
                    planetasObjectLiveData.postValue(result!!)
                } else {
                    Log.e("PlanetasViewModel", "El resultado es nulo")
                }
            }
        }
    }
}
