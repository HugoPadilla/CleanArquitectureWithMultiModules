package com.example.cleanarquitecturewithmodules.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.cleanarquitecturewithmodules.FakeData.Volumens
import com.example.cleanarquitecturewithmodules.ui.theme.CleanArquitectureWithModulesTheme
import com.example.cleanarquitecturewithmodules.R
import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo

@Composable
fun BooksScreen(viewModel: BookViewModel) {

    var listVolume = viewModel.books.collectAsState()
    BooksContent(
        volumens = listVolume.value,
        onItemClick = { },
        onNavigationNewBook = { }
    )
}

@Composable
fun BooksContent(
    volumens: List<Volume>,
    onItemClick: (id: String) -> Unit,
    onNavigationNewBook: () -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigationNewBook) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_library_add_24),
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Box(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { "Buscar libro" },
                value = "",
                onValueChange = {}
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {

                items(volumens) { volumen ->
                    CardBookRow(volume = volumen, onItemClick = onItemClick)
                }

            }
        }
    }
}

@Composable
fun CardBookRow(volume: Volume, onItemClick: (id: String) -> Unit) {

    val painter = rememberImagePainter(
        data = volume.volumeInfo.imageUrl,
        builder = {
            crossfade(true)
        }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(volume.id) },
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(74.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(style = MaterialTheme.typography.h5, text = volume.volumeInfo.title)
                Text(
                    style = MaterialTheme.typography.body2,
                    text = volume.volumeInfo.author[0]
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBooksContent() {
    CleanArquitectureWithModulesTheme {
        BooksContent(Volumens, onItemClick = {}, onNavigationNewBook = {})
    }
}

@Preview
@Composable
fun PreviewCardViewIten() {
    CleanArquitectureWithModulesTheme {
        CardBookRow(
            Volume(
                "12123",
                VolumeInfo(
                    title = "First Title",
                    author = listOf("Primer author", "Author dos"),
                    imageUrl = null
                )
            )
        ) {
            // OnClick
        }
    }
}