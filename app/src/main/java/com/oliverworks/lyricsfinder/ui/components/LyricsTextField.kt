package com.oliverworks.lyricsfinder.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LyricsTextField(
    modifier: Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        label = { Text(text = label) },
        value = value,
        onValueChange = {
            onValueChange(it) })
}