package com.example.cleanarquitecturewithmodules.di

import com.example.domain.repositories.BooksRepository
import com.example.domain.usecasesinteratores.GetAllBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ActivityModule {

    @Provides
    fun providerGetBookUseCase(
        booksRepository: BooksRepository
    ): GetAllBooksUseCase {
        return GetAllBooksUseCase(booksRepository)
    }

}