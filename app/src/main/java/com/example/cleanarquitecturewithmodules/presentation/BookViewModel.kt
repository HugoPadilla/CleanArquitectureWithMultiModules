package com.example.cleanarquitecturewithmodules.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.State
import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo
import com.example.domain.usecasesinteratores.GetAllBooksUseCase
import com.example.domain.usecasesinteratores.GetBookUseCase
import com.example.domain.usecasesinteratores.InsertBooksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookViewModel @ViewModelInject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getBookUseCase: GetBookUseCase,
    private val insertBooksUseCase: InsertBooksUseCase
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

    fun getBook(id: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            _books.postValue("")
            try {
                getBookUseCase(id).collect { listVolume ->
                    when (listVolume) {
                        is State.Failed -> _books.value = "Error: ${listVolume.message}"
                        is State.Loading -> _books.value = "Buscando libro"
                        is State.Success -> _books.value = listVolume.data.toString()
                    }
                }
            } catch (e: Throwable) {
                _books.postValue("Error al cargar datos: ${e.message}")
            }
            _dataLoading.postValue(false)
        }
    }

    fun setBook() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            _books.postValue("")
            try {
                insertBooksUseCase(
                    Volume(
                        "fskdjfw",
                        VolumeInfo("Nuevo libro", listOf("H. Berman", "Frank Bosterr"), null)
                    )
                ).collect { listVolume ->
                    when (listVolume) {
                        is State.Failed -> "Error: ${listVolume.message}"
                        is State.Loading -> _books.value = "Insertando nuevo libro"
                        is State.Success -> _books.value = listVolume.data.toString()
                    }

                }
            } catch (e: Throwable) {
                _books.postValue("Error al cargar datos: ${e.message}")
            }
            _dataLoading.postValue(false)
        }
    }

}