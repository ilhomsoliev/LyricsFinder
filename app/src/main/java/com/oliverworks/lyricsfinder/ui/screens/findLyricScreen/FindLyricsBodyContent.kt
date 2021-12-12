package com.oliverworks.lyricsfinder.ui.screens.findLyricScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oliverworks.lyricsfinder.ui.components.LyricsTextField
import com.oliverworks.lyricsfinder.ui.util.UiConstants

@Composable
fun FindLyricsBodyContent(
    modifier: Modifier,
    lyrics: String,
    onFindClick: (String, String) -> Unit,
    onSaveClick: () -> Unit
) {
    var artistName by remember { mutableStateOf("Queen") }
    var songName by remember { mutableStateOf("We will rock you") }
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        LyricsTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            label = "Type Artist Name",
            value = artistName,
            onValueChange = {
                artistName = it
            })

        LyricsTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            label = "Type song Name",
            value = songName,
            onValueChange = {
                songName = it
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            Button(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(bottom = 4.dp, top = 4.dp, end = 4.dp),
                onClick = { onFindClick(artistName, songName) }) {
                Text(text = "Find")
            }
            Button(modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 4.dp, top = 4.dp, start = 4.dp),
                onClick =  onSaveClick ) {
                Text(text = "Save")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(lyrics == UiConstants.LOADING_STATUS_ID){
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator()
            }
        }else {
            Text(text = lyrics)
        }
    }
}
