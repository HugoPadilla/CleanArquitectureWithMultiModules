package com.example.data.repositories

import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    fun getBookmarks(): Flow<List<Volume>>

    fun bookmark(book: Volume)

    fun unbookmark(book: Volume)
}