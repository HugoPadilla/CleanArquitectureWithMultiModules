package com.example.domain.repositories

import com.example.domain.common.State
import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooks(): Flow<List<Volume>>

    suspend fun getBookById(id: String): Flow<State<Volume>>

    suspend fun insertBook(volume: Volume): Flow<State<String>>

    suspend fun getBookmarks(): Flow<List<Volume>>

    suspend fun bookmark(book: Volume)

    suspend fun unBookmark(book: Volume)
}