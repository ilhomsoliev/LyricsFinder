package com.oliverworks.lyricsfinder.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun FindLyricScreen() {
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
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Favorite, contentDescription = "")
            }
        }
    ) { innerPadding ->
        FindLyricsBodyContent(Modifier.padding(innerPadding))
    }
    /*LyricAppToolbar(modifier = Modifier.background(Color.Blue), title = "Hey here it is") {
        Icon(painter = painterResource(R.drawable.ic_baseline_more_vert_24), contentDescription = "")
    }*/
}

@Composable
fun FindLyricsBodyContent(modifier: Modifier) {
    var artistName by remember { mutableStateOf("") }
    var songName by remember { mutableStateOf("") }
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = artistName,
            onValueChange = { artistName = it },
            label = { Text(text = "Type Artist name") })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = songName,
            onValueChange = { songName = it },
            label = { Text(text = "Type song name") })

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
            Button(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(bottom = 4.dp,top = 4.dp,end = 4.dp), onClick = { /*TODO*/ }) {
                Text(text = "Find")
            }
            Button(modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 4.dp,top = 4.dp, start = 4.dp), onClick = { /*TODO*/ }) {
                Text(text = "Save")
            }
        }
    }
}