package com.example.data.repositories

import com.example.data.entities.Book
import com.example.data.entities.BookEntity
import com.example.domain.common.State
import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow

interface BooksRemoteDataSource {
    suspend fun getBooks(): Flow<List<BookEntity>>
    suspend fun insertBook(book: Book): Flow<State<String>>
    suspend fun getBookById(id: String): Flow<State<Volume>>
}