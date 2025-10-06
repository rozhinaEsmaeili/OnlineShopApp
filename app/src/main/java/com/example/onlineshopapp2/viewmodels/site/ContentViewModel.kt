package com.example.onlineshopapp2.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.site.Content
import com.example.onlineshopapp2.repositories.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val repository: ContentRepository) :
    ViewModel() {

    fun getContent(onResponse: (reposnse: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getContent()
            onResponse(response)
        }
    }

    fun getContentByTitle(title: String, onResponse: (reposnse: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getContentByTitle(title)
            onResponse(response)
        }
    }
}