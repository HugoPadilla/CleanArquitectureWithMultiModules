package com.example.data.repositories

import com.example.data.entities.BookEntity
import kotlinx.coroutines.flow.Flow

interface BooksRemoteDataSource {
    suspend fun getBooks(): Flow<List<BookEntity>>
}