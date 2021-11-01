package com.example.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.data.entities.BookEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {
    @Query("SELECT * FROM book WHERE title == :title")
    fun getClient(title: String): Flow<List<BookEntity>>
}