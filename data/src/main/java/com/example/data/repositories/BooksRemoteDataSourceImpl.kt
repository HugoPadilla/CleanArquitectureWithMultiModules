package com.example.data.repositories

import com.example.data.apiservice.RemoteDataFirestore
import com.example.data.entities.BookEntity
import com.example.data.entities.toBookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BooksRemoteDataSourceImpl(private val remoteDataFirestore: RemoteDataFirestore) :
    BooksRemoteDataSource {
    override suspend fun getBooks(): Flow<List<BookEntity>> {
        return remoteDataFirestore.getBooks().map { listBook ->
            listBook.map { book ->
                toBookEntity(book)
            }
        }
    }
}