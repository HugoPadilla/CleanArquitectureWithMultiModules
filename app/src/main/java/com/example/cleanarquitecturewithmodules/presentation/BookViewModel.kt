package com.example.cleanarquitecturewithmodules.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecasesinteratores.GetAllBooksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookViewModel @ViewModelInject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _books = MutableLiveData<String>()
    val books: LiveData<String> = _books

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    //private val _remoteBooks = arrayListOf<Volume>()

    fun getBooks(author: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            _books.postValue("")
            try {
                getAllBooksUseCase(author).collect { listVolume ->
                    _books.value = listVolume.toString()
                }
            } catch (e: Throwable) {
                _books.postValue("Error al cargar datos: ${e.message}")
            }
            _dataLoading.postValue(false)
        }
    }

}