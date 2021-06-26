package com.example.domain.repositories

import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getRemoteBooks(author: String): List<Volume>

    suspend fun getBookmarks(): Flow<List<Volume>>

    suspend fun bookmark(book: Volume)

    suspend fun unBookmark(book: Volume)
}