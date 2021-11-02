package com.example.data.apiservice

import com.example.data.entities.Book
import com.example.domain.common.State
import kotlinx.coroutines.flow.Flow

interface RemoteDataFirestore {
    suspend fun getBooks(): Flow<List<Book>>
    suspend fun insertBook(book: Book): Flow<State<String>>
    suspend fun getBookById(id: String): Flow<State<Book>>
}