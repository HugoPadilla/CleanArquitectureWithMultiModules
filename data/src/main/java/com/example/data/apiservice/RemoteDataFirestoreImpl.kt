package com.example.data.apiservice

import com.example.data.entities.Book
import com.example.domain.common.State
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

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

    override suspend fun insertBook(book: Book): Flow<State<String>> = flow {
        emit(State.loading())
        var documentRef = db.collection("books").document()

        documentRef.set(book).await()
        emit(State.success("Se agregó un nuevo libro"))

    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getBookById(id: String): Flow<State<Book>> = callbackFlow {

        offer(State.loading())

        val query = db.collection("books").document(id)
        val listener = query.addSnapshotListener { documentSnapshot, error ->

            if (documentSnapshot != null && documentSnapshot.exists()) {
                offer(State.success(documentSnapshot.toObject(Book::class.java)))
            }

            error?.let {
                offer(State.failed(it.message.toString()))
                cancel(it.message.toString())
            }
        }

        awaitClose {
            listener.remove()
            cancel()
        }
    } as Flow<State<Book>>
}