package com.oliverworks.lyricsfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.oliverworks.lyricsfinder.ui.theme.LyricsFinderTheme
import com.oliverworks.lyricsfinder.ui.util.Navigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsFinderTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
    }
}
