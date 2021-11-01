package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}