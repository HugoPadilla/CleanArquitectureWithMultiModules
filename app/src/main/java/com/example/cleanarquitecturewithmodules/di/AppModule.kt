package com.example.cleanarquitecturewithmodules.di

import android.content.Context
import androidx.room.Room
import com.example.data.apiservice.RemoteDataFirestoreImpl
import com.example.data.apiservice.RemoteDataFirestore
import com.example.data.db.RoomDatabase
import com.example.data.repositories.*
import com.example.domain.repositories.BooksRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun providerFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun providerRemoteDataFirestore(db: FirebaseFirestore): RemoteDataFirestore {
        return RemoteDataFirestoreImpl(db)
    }

    @Singleton
    @Provides
    fun providerBooksRemoteDataSource(remoteDataFirestore: RemoteDataFirestore): BooksRemoteDataSource {
        return BooksRemoteDataSourceImpl(remoteDataFirestore)
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