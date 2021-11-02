package com.example.data.apiservice

import com.example.data.entities.Book
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RemoteDataFirestoreImpl(private val db: FirebaseFirestore) : RemoteDataFirestore {
    override suspend fun getBooks(): Flow<List<Book>> = callbackFlow {
        val query = db.collection("books").orderBy(Book::title.name, Query.Direction.DESCENDING)

        val listener = query.addSnapshotListener { document, error ->

            if (document != null) {
                offer(document.toObjects(Book::class.java))
            }

            error?.let {
                cancel(it.message.toString())
            }
        }

        awaitClose {
            listener.remove()
            cancel()
        }
    }
}