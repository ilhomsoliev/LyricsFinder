package com.oliverworks.lyricsfinder.ui.screens.favoriteListScreen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oliverworks.lyricsfinder.models.Music

@Composable
fun FavoriteLyricsScreen(
    navController: NavController,
    viewModel: FavoriteLyricsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favorites")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        val list: State<List<Music>?> = viewModel.lyrics.observeAsState()
        if (list.value != null) {
            FavoriteLyricsBodyContent(list = list.value!!, onDelete = {
                viewModel.deleteMusic(it)
            })
        }
    }
}