package com.example.onlineshopapp2.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.site.Blog
import com.example.onlineshopapp2.repositories.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: BlogRepository) : ViewModel() {

    fun getBlog(onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getBlog()
            onResponse(response)
        }
    }

    fun getBlogById(id: Long, onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getBlogById(id)
            onResponse(response)
        }
    }
}