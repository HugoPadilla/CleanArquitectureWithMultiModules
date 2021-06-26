package com.example.data.repositories

import com.example.data.entities.BookEntity

class BooksRemoteDataSourceImpl: BooksRemoteDataSource {
    override fun getBooks(author: String): List<BookEntity> {
        return listOf<BookEntity>(
            BookEntity("1234","Titulo 1", listOf("Payton Woolley", "Jayce Mcmahon"), null),
            BookEntity("1353", "Titulo 2", listOf("Braiden Hussain", "Stevie Barber"), null)
        )
    }
}