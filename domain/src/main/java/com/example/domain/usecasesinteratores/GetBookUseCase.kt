package com.example.domain.usecasesinteratores

import com.example.domain.common.State
import com.example.domain.entities.Volume
import com.example.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow

class GetBookUseCase(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(id: String): Flow<State<Volume>> = booksRepository.getBookById(id)
}