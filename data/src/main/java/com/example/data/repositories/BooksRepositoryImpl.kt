package com.example.data.repositories

import com.example.data.mappers.BookEntityMapper
import com.example.domain.common.State
import com.example.domain.entities.Volume
import com.example.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BooksRepositoryImpl(
    private val remoteDataSource: BooksRemoteDataSource,
    private val localDataSource: BooksLocalDataSource
) : BooksRepository {
    override suspend fun getBooks(): Flow<List<Volume>> {
        val mapper = BookEntityMapper()
        return remoteDataSource.getBooks().map { value -> value.map { mapper.toVolume(it) } }
    }

    override suspend fun getBookById(id: String): Flow<State<Volume>> {
        return remoteDataSource.getBookById(id)
    }

    override suspend fun insertBook(volume: Volume): Flow<State<String>> {
        val mapper = BookEntityMapper()
        return remoteDataSource.insertBook(mapper.toBook(volume))
    }

    override suspend fun getBookmarks(): Flow<List<Volume>> {
        return localDataSource.getBookmarks()
    }

    override suspend fun bookmark(book: Volume) {
        localDataSource.bookmark(book)
    }

    override suspend fun unBookmark(book: Volume) {
        localDataSource.unbookmark(book)
    }

}