package com.oliverworks.lyricsfinder.ui.screens.favoriteListScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oliverworks.lyricsfinder.models.Music
import kotlin.math.min


@Composable
fun FavoriteLyricsBodyContent(list: List<Music>, onDelete: (Music) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { it ->
            MusicItemView(it) {
                onDelete(it)
            }
        }
    }
}

@Composable
fun MusicItemView(music: Music, onDelete: (Music) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(elevation = 4.dp, modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        .clickable {
            expanded = !expanded
        }
        .padding(6.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(modifier = Modifier.padding(4.dp), text = music.artist)
                IconButton(modifier = Modifier
                    .padding(4.dp), onClick = {
                    onDelete(music)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }

            }
            if (expanded) {
                Text(modifier = Modifier.padding(4.dp), text = music.lyric)
            } else {
                Text(
                    modifier = Modifier.padding(4.dp), text = music.lyric.subSequence(
                        0,
                        min(100, music.lyric.length - 1)
                    ).toString() + "...show more"
                )
            }
        }
    }
}