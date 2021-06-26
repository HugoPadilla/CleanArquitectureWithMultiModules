package com.example.data.repositories

import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksLocalDataSourceImpl: BooksLocalDataSource {
    override fun getBookmarks(): Flow<List<Volume>> = flow {
        emit(listOf<Volume>(
            Volume("1234", VolumeInfo("Titulo 1", listOf("fisrt Name"), null)),
            Volume("1353", VolumeInfo("Titulo 2", listOf("Aeme", "Bot D."), null))
        ))
    }

    override fun bookmark(book: Volume) {
        TODO("Not yet implemented")
    }

    override fun unbookmark(book: Volume) {
        TODO("Not yet implemented")
    }
}