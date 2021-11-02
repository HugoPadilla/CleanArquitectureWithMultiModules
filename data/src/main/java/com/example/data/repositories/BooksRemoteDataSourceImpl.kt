package com.example.data.repositories

import com.example.data.apiservice.RemoteDataFirestore
import com.example.data.entities.Book
import com.example.data.entities.BookEntity
import com.example.data.entities.toBookEntity
import com.example.data.entities.toVolume
import com.example.domain.common.State
import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BooksRemoteDataSourceImpl(
    private val remoteDataFirestore: RemoteDataFirestore
) :
    BooksRemoteDataSource {
    override suspend fun getBooks(): Flow<List<BookEntity>> {
        return remoteDataFirestore.getBooks().map { listBook ->
            listBook.map { book ->
                toBookEntity(book)
            }
        }
    }

    override suspend fun insertBook(book: Book): Flow<State<String>> {
        return remoteDataFirestore.insertBook(book)
    }

    override suspend fun getBookById(id: String): Flow<State<Volume>> {
        return remoteDataFirestore.getBookById(id).map { result ->
            return@map when (result) {
                is State.Failed -> State.failed(result.message)
                is State.Loading -> State.loading()
                is State.Success -> State.Success(toVolume(result.data))
            }
        }
    }
}