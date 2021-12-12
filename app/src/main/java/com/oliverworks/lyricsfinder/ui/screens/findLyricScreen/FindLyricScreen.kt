package com.oliverworks.lyricsfinder.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oliverworks.lyricsfinder.ui.screens.findLyricScreen.FindLyricScreenViewModel
import com.oliverworks.lyricsfinder.ui.screens.findLyricScreen.FindLyricsBodyContent
import com.oliverworks.lyricsfinder.ui.util.Screen
import com.oliverworks.lyricsfinder.ui.util.UiConstants
import com.oliverworks.lyricsfinder.util.Status
import kotlinx.coroutines.launch


@Composable
fun FindLyricScreen(
    navController: NavController,
    viewModel: FindLyricScreenViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lyric Finder")
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.FavoriteLyricsScreen.route)
            }) {
                Icon(Icons.Default.Favorite, contentDescription = "", tint = Color.White)
            }
        }
    ) { innerPadding ->

        val lyrics by viewModel.lyrics.observeAsState()
        FindLyricsBodyContent(
            Modifier.padding(innerPadding),
            when (lyrics?.status) {
                Status.SUCCESS -> {
                    lyrics?.data?.lyrics
                }
                Status.CALM -> {
                    "Fill blanks to search."
                }
                Status.LOADING -> {
                    UiConstants.LOADING_STATUS_ID
                }
                else -> {
                    "Some error or there is no such music"
                }
            } as String,
            { artistName, songName ->
                viewModel.setArtistName(artistName)
                viewModel.setLyricsLoading()
                viewModel.getLyrics(artistName, songName)
            },
            {
                if (viewModel.lyrics.value!!.status == Status.SUCCESS) {
                    viewModel.saveLyrics()
                }else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Can's save")
                        /*DefaultSnackbar(snackbarHostState = snackbarHostState) {

                        }*/
                    }
                }
            })
        SnackbarHost(hostState = snackbarHostState)
    }
}
