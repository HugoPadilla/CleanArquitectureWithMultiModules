package com.example.data.entities

import com.google.firebase.firestore.DocumentId

data class Book(
    @DocumentId
    val id: String? = null,
    val title: String = "",
    val authors: List<String> = listOf(),
    val imageUrl: String? = null
)

fun toBookEntity(book: Book): BookEntity {
    return BookEntity(
        id = book.id.toString(),
        title = book.title,
        authors = book.authors,
        imageUrl = book.imageUrl
    )
}