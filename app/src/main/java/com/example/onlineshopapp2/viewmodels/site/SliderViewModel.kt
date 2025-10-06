package com.example.onlineshopapp2.viewmodels.site

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.site.Slider
import com.example.onlineshopapp2.repositories.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private val repository: SliderRepository) : ViewModel() {

    var dataList = mutableStateOf<List<Slider>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getSlider { response ->
            isLoading.value = false
            if (response.status == "OK".uppercase()) {
                dataList.value = response.data!!
            }
        }
    }

    fun getSlider(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getSlider()
            onResponse(response)
        }
    }

    fun getSliderById(id: Long, onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getSliderById(id)
            onResponse(response)
        }
    }
}