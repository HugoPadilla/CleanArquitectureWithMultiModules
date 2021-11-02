package com.example.data.repositories

import com.example.data.mappers.BookEntityMapper
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
        /*
        val recipient = ArrayList<Volume>()
        val result = remoteDataSource.getBooks()
        result.map {
            recipient.add(mapper.toVolume(it))
        }
        return recipient*/
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