package com.example.domain.usecasesinteratores

import com.example.domain.entities.Volume
import com.example.domain.repositories.BooksRepository

class GetBookUseCase (
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke (author: String): List<Volume> = booksRepository.getRemoteBooks(author)
}