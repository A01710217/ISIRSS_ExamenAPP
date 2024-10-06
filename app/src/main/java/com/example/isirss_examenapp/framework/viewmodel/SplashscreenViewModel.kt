package com.example.isirss_examenapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.isirss_examenapp.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashscreenViewModel:ViewModel() {
    val finishedLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        finishedLoading.postValue(false)
        viewModelScope.launch {
            delay(Constants.SPLASHCREEN_DURATION)
            finishedLoading.postValue(true)
        }
    }
}