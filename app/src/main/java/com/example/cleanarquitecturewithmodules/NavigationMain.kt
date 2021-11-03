package com.example.cleanarquitecturewithmodules

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cleanarquitecturewithmodules.presentation.BookViewModel
import com.example.cleanarquitecturewithmodules.presentation.BooksScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavigationMain(modifier: Modifier, navController: NavHostController) {

    NavHost(modifier = modifier, navController = navController, startDestination = "book_list") {

        composable("book_list") {
            val viewModel = hiltViewModel<BookViewModel>()
            BooksScreen(viewModel = viewModel)
        }

        composable("book_detail") {

        }

        composable("book_new") {

        }

    }

}