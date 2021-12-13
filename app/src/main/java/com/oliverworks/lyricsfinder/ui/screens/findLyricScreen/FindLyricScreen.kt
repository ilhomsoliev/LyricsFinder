package com.oliverworks.lyricsfinder.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
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
    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }
    val menuItems = listOf("Favorites", "Settings", "About")
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lyric Finder")
                },
                actions = {
                    IconButton(onClick = { expanded.value = true}) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "",tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = expanded.value,
                        offset = DpOffset((-40).dp, (-40).dp),
                        onDismissRequest = { expanded.value = false }) {
                        menuItems.forEach {
                            DropdownMenuItem(onClick = {
                                /*Toast.makeText(
                                    context,
                                    "You clicked $it menu",
                                    Toast.LENGTH_LONG
                                ).show()*/
                                expanded.value = false
                            }) {
                                Text(text = it)
                            }
                        }
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
            modifier = Modifier.padding(innerPadding),
            lyrics = when (lyrics?.status) {
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
            onFindClick = { artistName, songName ->
                viewModel.setArtistName(artistName)
                viewModel.setLyricsLoading()
                viewModel.getLyrics(artistName, songName)
            },
            onSaveClick = {
                if (viewModel.lyrics.value!!.status == Status.SUCCESS) {
                    viewModel.saveLyrics()
                }else {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Can's save")
                    }
                }
            })
            SnackbarHost(hostState = snackbarHostState)
    }
}
