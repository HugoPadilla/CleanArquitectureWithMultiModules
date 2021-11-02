package com.example.domain.usecasesinteratores

import com.example.domain.entities.Volume
import com.example.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetAllBooksUseCase(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(author: String): Flow<List<Volume>> = booksRepository.getBooks()
}