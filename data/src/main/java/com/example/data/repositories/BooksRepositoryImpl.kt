package com.example.data.repositories

import com.example.data.mappers.BookEntityMapper
import com.example.domain.entities.Volume
import com.example.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow

class BooksRepositoryImpl (
    private val remoteDataSource: BooksRemoteDataSource,
    private val localDataSource: BooksLocalDataSource
): BooksRepository {
    override suspend fun getRemoteBooks(author: String): List<Volume> {

        val mapper = BookEntityMapper()
        val recipient = ArrayList<Volume>()
        val result = remoteDataSource.getBooks(author)
        result.map {
            recipient.add(mapper.toVolume(it))
        }
        return recipient
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