package com.oliverworks.lyricsfinder.ui.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oliverworks.lyricsfinder.ui.screens.FavoriteLyricsScreen
import com.oliverworks.lyricsfinder.ui.screens.FindLyricScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.FindLyricScreen.route){
        composable(Screen.FindLyricScreen.route){
            FindLyricScreen()
        }
        composable(Screen.FavoriteLyricsScreen.route){
            FavoriteLyricsScreen()
        }
    }
}