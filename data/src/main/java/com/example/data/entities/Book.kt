package com.example.data.entities

import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo
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

fun toVolume(book: Book): Volume {
    return Volume(
        id = book.id.toString(),
        volumeInfo = VolumeInfo(title = book.title, author = book.authors, imageUrl = book.imageUrl)
    )
}