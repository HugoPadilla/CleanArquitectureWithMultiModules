package com.example.data.repositories

import com.example.data.entities.BookEntity

interface BooksRemoteDataSource {
    fun getBooks(author: String): List<BookEntity>
}