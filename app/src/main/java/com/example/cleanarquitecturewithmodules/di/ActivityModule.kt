package com.example.cleanarquitecturewithmodules.di

import com.example.domain.repositories.BooksRepository
import com.example.domain.usecasesinteratores.GetAllBooksUseCase
import com.example.domain.usecasesinteratores.GetBookUseCase
import com.example.domain.usecasesinteratores.InsertBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ActivityModule {

    @Provides
    fun providerGetAllBooksUseCase(
        booksRepository: BooksRepository
    ): GetAllBooksUseCase {
        return GetAllBooksUseCase(booksRepository)
    }

    @Provides
    fun providerGetBookUseCase(
        booksRepository: BooksRepository
    ): GetBookUseCase {
        return GetBookUseCase(booksRepository)
    }

    @Provides
    fun providerInsertBookUseCase(
        booksRepository: BooksRepository
    ): InsertBooksUseCase {
        return InsertBooksUseCase(booksRepository)
    }
}