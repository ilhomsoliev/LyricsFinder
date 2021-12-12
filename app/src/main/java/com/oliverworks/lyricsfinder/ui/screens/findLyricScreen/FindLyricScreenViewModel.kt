package com.oliverworks.lyricsfinder.ui.screens.findLyricScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliverworks.lyricsfinder.database.MusicDatabaseRepository
import com.oliverworks.lyricsfinder.models.Lyrics
import com.oliverworks.lyricsfinder.models.Music
import com.oliverworks.lyricsfinder.network.MusicNetworkRepository
import com.oliverworks.lyricsfinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindLyricScreenViewModel @Inject constructor(
    private val musicNetworkRepository: MusicNetworkRepository,
    private val databaseDatabaseRepository: MusicDatabaseRepository
) : ViewModel() {
    private val _lyrics = MutableLiveData<Resource<Lyrics>>(Resource.calm(null))
    var lyrics: LiveData<Resource<Lyrics>> = _lyrics
    private val _artistName = MutableLiveData<String>("")
    var artistName: LiveData<String> = _artistName
    fun getLyrics(artistName: String, songName: String) {
        viewModelScope.launch {
            _lyrics.value = musicNetworkRepository.getLyrics(artistName, songName)
        }
    }

    fun setLyricsLoading() {
        _lyrics.value = Resource.loading(null)
    }

    fun saveLyrics() = viewModelScope.launch {
        databaseDatabaseRepository.insertMusic(
            Music(
                artist = _artistName.value.toString(),
                name = "",
                lyric = _lyrics.value!!.data!!.lyrics.toString()
            )
        )

    }

    fun setArtistName(artistName: String) {
        _artistName.value = artistName
    }
}