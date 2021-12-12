package com.oliverworks.lyricsfinder.ui.screens.favoriteListScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliverworks.lyricsfinder.database.MusicDatabaseRepository
import com.oliverworks.lyricsfinder.models.Music
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteLyricsViewModel @Inject constructor(
    private val databaseDatabaseRepository: MusicDatabaseRepository
) : ViewModel() {

    private val _lyrics = MutableLiveData<List<Music>>()
    val lyrics: LiveData<List<Music>> = _lyrics

    init {
        loadSavedLyrics()
    }

    private fun loadSavedLyrics() = viewModelScope.launch {
        _lyrics.value = databaseDatabaseRepository.getAllMusics()
    }

    fun deleteMusic(music: Music) = viewModelScope.launch {
        databaseDatabaseRepository.deleteMusic(music)
        loadSavedLyrics()
    }

}