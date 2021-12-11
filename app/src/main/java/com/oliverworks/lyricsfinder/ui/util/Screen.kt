package com.oliverworks.lyricsfinder.ui.util

import androidx.annotation.VisibleForTesting
import com.oliverworks.lyricsfinder.ui.screens.FindLyricScreen

sealed class Screen(val route:String){
    object FindLyricScreen:Screen(UiConstants.FIND_LYRIC_SCREEN_ID)
    object FavoriteLyricsScreen:Screen(UiConstants.FAVORITE_LYRICS_SCREEN_ID)

}