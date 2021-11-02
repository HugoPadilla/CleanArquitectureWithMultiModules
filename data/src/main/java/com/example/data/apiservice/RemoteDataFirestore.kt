package com.example.data.apiservice

import com.example.data.entities.Book
import kotlinx.coroutines.flow.Flow

interface RemoteDataFirestore {

    suspend fun getBooks(): Flow<List<Book>>
}