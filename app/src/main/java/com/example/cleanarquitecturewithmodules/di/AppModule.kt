package com.example.cleanarquitecturewithmodules.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.RoomDatabase
import com.example.data.repositories.*
import com.example.domain.repositories.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providerRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "table_books"
        ).build()

    @Singleton
    @Provides
    fun provideBookDao(db: RoomDatabase) = db.bookDao()
}