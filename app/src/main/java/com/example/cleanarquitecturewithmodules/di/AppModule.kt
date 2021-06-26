package com.example.cleanarquitecturewithmodules.di

import com.example.data.repositories.*
import com.example.domain.repositories.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerBooksRemoteDataSource(): BooksRemoteDataSource {
        return BooksRemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providerBooksLocalDataSource(): BooksLocalDataSource {
        return BooksLocalDataSourceImpl()
    }

    @Singleton
    @Provides
    fun providerBooksRepository(
        booksRemoteDataSource: BooksRemoteDataSource,
        booksLocalDataSource: BooksLocalDataSource
    ): BooksRepository {
        return BooksRepositoryImpl(booksRemoteDataSource, booksLocalDataSource)
    }

}